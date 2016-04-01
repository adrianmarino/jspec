package ar.com.nonosoft.jspec.container.describe.impl;

import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.block.ParentDescribeBlock;
import ar.com.nonosoft.jspec.container.describe.Description;
import ar.com.nonosoft.jspec.output.report.Report;
import ar.com.nonosoft.jspec.util.StringUtils;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class ParentDescribe<SUBJECT> extends Description<SUBJECT> {

	public void describe(String desc, VoidBlock block) {
		new ChildDescribe<SUBJECT>(desc, block, this, report);
	}

	protected void printFooter() {
		report.output().endLevel();
	}

	protected void printHeader() {
		report.output().println(StringUtils.boldWithFbColor(description(), DEFAULT)).beginLevel();
	}

	public ParentDescribe(Class<SUBJECT> clazz, ParentDescribeBlock<SUBJECT> block, Report report) {
		this(clazz.getName(), block, report);
	}

	public ParentDescribe(String description, ParentDescribeBlock<SUBJECT> block, Report report) {
		super(description, report);
		perform(() -> block.eval(this));
	}

	public void run() {
		its().forEach(it->it.run());
	}
}
