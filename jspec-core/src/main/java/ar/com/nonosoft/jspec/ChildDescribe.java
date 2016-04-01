package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.output.report.Report;
import ar.com.nonosoft.jspec.util.StringUtils;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class ChildDescribe<SUBJECT> extends Describe<SUBJECT> {

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	protected void printFooter() {
		report.output().endLevel();
	}

	protected void printHeader() {
		report.output().println(StringUtils.boldWithFbColor(description(), DEFAULT)).beginLevel();
	}

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	ChildDescribe(String description, VoidBlock block, Container parent, Report report) {
		super(description, parent, report);
		block.eval();
	}
}
