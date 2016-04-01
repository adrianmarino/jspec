package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentContextBlock;
import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.output.report.Report;

public abstract class Describe<SUBJECT> extends Container<Describe<SUBJECT>, SUBJECT> {

	/**
	 * Contexts are a powerful method to make your tests clear and well organized.
	 * In the long term this practice will keep tests easy to read.
	 * A context can contain an other context and its. When describing a context,
	 * start its description with "when" or "with".
	 *
	 * @see <a href="http://betterspecs.org/#contexts">Use contexts</a>
	 */
	public void context(String description, ParentContextBlock<SUBJECT> block) {
		new ParentContext<>(description, block, this, report);
	}

	/**
	 * Be clear about what method you are describing. For instance,
	 * use a convention prefix of . when referring to a class method's name and
	 * # when referring to an instance method's name.
	 *
	 * @see <a href="http://betterspecs.org/#describe">How to describe your methods</a>
	 */
	public void describe(String description, VoidBlock block) {
		new ChildDescribe<>(description, block, this, report);
	}

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	Describe(String description, Container parent, Report report) {
		super(description, parent, report);
	}

	Describe(String description, Report report) {
		super(description, report);
	}
}
