package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentDescribeBlock;
import ar.com.nonosoft.jspec.exception.impl.MultipleRootDescriptions;
import ar.com.nonosoft.jspec.output.Report;
import org.junit.runner.RunWith;

import java.util.List;

import static ar.com.nonosoft.jspec.util.ClassUtils.subclassGenericParamOf;

/**
 * The base class for all JSpec specifications.
 *
 * @param <SUBJECT>
 *          Generic type of class to test.
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
	// Private Methods
	// --------------------------------------------------------------------------

	private ParentDescribe<SUBJECT> newRootDescription(ParentDescribeBlock<SUBJECT> block) {
		return new ParentDescribe<>(subclassGenericParamOf(getClass()).getTypeName(), block, report);
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
