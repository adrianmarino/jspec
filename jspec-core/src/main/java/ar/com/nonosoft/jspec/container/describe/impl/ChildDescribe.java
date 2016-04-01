package ar.com.nonosoft.jspec.container.describe.impl;

import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.container.Container;
import ar.com.nonosoft.jspec.container.describe.Description;
import ar.com.nonosoft.jspec.output.report.Report;
import ar.com.nonosoft.jspec.util.StringUtils;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class ChildDescribe<SUBJECT> extends Description<SUBJECT> {

	public ChildDescribe(String description, VoidBlock block, Container parent, Report report) {
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
