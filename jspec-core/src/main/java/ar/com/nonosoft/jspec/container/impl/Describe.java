package ar.com.nonosoft.jspec.container.impl;

import ar.com.nonosoft.jspec.block.Block;
import ar.com.nonosoft.jspec.container.Container;
import ar.com.nonosoft.jspec.output.report.Report;

import static ar.com.nonosoft.jspec.util.StringUtils.boldWithFbColor;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class Describe<SUBJECT> extends Container<SUBJECT> {

	protected void printHeader() {
		report.output().println(boldWithFbColor(description(), DEFAULT)).beginLevel();
	}

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Describe(String description, Report report) {
		super(description, () -> {}, report);
	}

	public Describe(String description, Block block, Report report) {
		super(description, block, report);
	}

	public Describe(String description, Block block, Container parent, Report report) {
		super(description, block, parent, report);
	}
}
