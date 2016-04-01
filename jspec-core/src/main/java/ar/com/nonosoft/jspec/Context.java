package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.output.report.Report;

import static ar.com.nonosoft.jspec.util.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public abstract class Context<SUBJECT> extends Container<Context<SUBJECT>,SUBJECT> {

	/**
	 * Contexts are a powerful method to make your tests clear and well organized.
	 * In the long term this practice will keep tests easy to read.
	 * A context can contain an other context and its. When describing a context,
	 * start its description with "when" or "with".
	 *
	 * @see <a href="http://betterspecs.org/#contexts">Use contexts</a>
	 */
	public void context(String desc, VoidBlock block) {
		new ChildContext<SUBJECT>(desc, block, this, report);
	}

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	protected void printHeader() {
		report.output().println(withFgColor(capitalize(description()), DEFAULT)).beginLevel();
	}

	protected void printFooter() {
		report.output().endLevel();
	}

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	Context(String description, Container parent, Report report) {
		super(description, parent, report);
	}

	Context(String description, Report report) {
		super(description, report);
	}
}
