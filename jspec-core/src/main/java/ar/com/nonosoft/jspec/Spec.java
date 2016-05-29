package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentDescribeBlock;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.exception.missing.block.impl.MultipleRootDescriptionsException;
import ar.com.nonosoft.jspec.output.Report;
import ar.com.nonosoft.jspec.test.Test;
import ar.com.nonosoft.jspec.util.ClassUtils;
import org.junit.runner.RunWith;
import java.util.List;

/**
 * The base class for all JSpec specifications.
 *
 * @param <SUBJECT> Generic type of class to test.
 */
@RunWith(JSpecRunner.class)
@SuppressWarnings("unchecked")
public abstract class Spec<SUBJECT> {

	/**
	 * Is the main block on a specification description. Use only on root description by spec.
	 */
	public void describe(ParentDescribeBlock<SUBJECT> block) {
		if (description != null) throw new MultipleRootDescriptionsException(getClass());
		description = newRootDescription(block);
	}

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	List<Test> tests() {
		return description.tests();
	}

	void run(Report anOtherReport) {
		tests().forEach(Test::run);
		anOtherReport.syncWith(report);
	}

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	/**
	 * Get class of object specified
	 */
	protected String describedClassName() {
		try {
			return ClassUtils.nameOfSubclass(getClass());
		} catch (ClassNotFoundException e) {
			throw new JSpecException("Error when resolve subject class!", e);
		}
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private ParentDescribe<SUBJECT> newRootDescription(ParentDescribeBlock<SUBJECT> block) {
		return new ParentDescribe<>(describedClassName(), block, report);
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private Report report;

	private ParentDescribe description;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Spec() {
		report = new Report();
	}
}
