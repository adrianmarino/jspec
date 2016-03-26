package ar.com.nonosoft.test.jspec.component.description.impl;

import ar.com.nonosoft.test.jspec.block.describe.NestedDescribeBlock;
import ar.com.nonosoft.test.jspec.component.Component;
import ar.com.nonosoft.test.jspec.component.description.Description;
import ar.com.nonosoft.test.jspec.output.report.Report;

import static ar.com.nonosoft.test.jspec.util.StringUtils.boldWithFbColor;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class NestedDescription<SUBJECT> extends Description<SUBJECT> {

	public NestedDescription(String description, NestedDescribeBlock block, Component parent, Report report) {
		super(description, parent, report);
		block.eval();
	}

	protected void printFooter() {
		report.output().endLevel();
	}

	protected void printHeader() {
		report.output().println(boldWithFbColor(description(), DEFAULT)).beginLevel();
	}
}
