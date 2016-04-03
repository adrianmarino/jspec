package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentDescribeBlock;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.exception.missing.block.impl.MultipleRootDescriptions;
import ar.com.nonosoft.jspec.output.Report;
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
		if (description != null) throw new MultipleRootDescriptions(getClass());
		description = newRootDescription(block);
	}

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	List<It> its() {
		return description.its();
	}

	void run(Report anOtherReport) {
		its().forEach(It::run);
		anOtherReport.syncWith(report);
	}

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	/**
	 * Get class of object specified
	 */
	protected Class<SUBJECT> describedClass() {
		try {
			return (Class<SUBJECT>) ClassUtils.genericClassOfSubclass(getClass());
		} catch (ClassNotFoundException e) {
			throw new JSpecException("Error when resolve subject class!", e);
		}
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private ParentDescribe<SUBJECT> newRootDescription(ParentDescribeBlock<SUBJECT> block) {
		return new ParentDescribe<>(describedClass().getName(), block, report);
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
