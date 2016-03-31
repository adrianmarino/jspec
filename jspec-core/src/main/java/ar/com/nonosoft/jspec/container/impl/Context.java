package ar.com.nonosoft.jspec.container.impl;

import ar.com.nonosoft.jspec.block.Block;
import ar.com.nonosoft.jspec.container.Container;
import ar.com.nonosoft.jspec.output.report.Report;

import static ar.com.nonosoft.jspec.util.StringUtils.withFgColor;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class Context<SUBJECT> extends Container<SUBJECT> {

	protected void printHeader() {
		report.output().println(withFgColor(description(), DEFAULT)).beginLevel();
	}

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Context(String description, Block block, Report report) {
		super(description, block, report);
	}

	public Context(String description, Block block, Container parent, Report report) {
		super(description, block, parent, report);
	}
}
