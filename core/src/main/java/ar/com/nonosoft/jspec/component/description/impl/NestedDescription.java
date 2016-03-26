package ar.com.nonosoft.jspec.component.description.impl;

import ar.com.nonosoft.jspec.block.describe.NestedDescribeBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.component.description.Description;
import ar.com.nonosoft.jspec.output.report.Report;
import ar.com.nonosoft.jspec.util.StringUtils;
import ar.com.nonosoft.jspec.block.describe.NestedDescribeBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.component.description.Description;
import ar.com.nonosoft.jspec.output.report.Report;

import static ar.com.nonosoft.jspec.util.StringUtils.boldWithFbColor;
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
		report.output().println(StringUtils.boldWithFbColor(description(), DEFAULT)).beginLevel();
	}
}
