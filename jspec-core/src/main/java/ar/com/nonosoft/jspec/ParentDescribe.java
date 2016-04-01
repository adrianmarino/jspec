package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.block.ParentDescribeBlock;
import ar.com.nonosoft.jspec.output.report.Report;
import ar.com.nonosoft.jspec.util.StringUtils;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class ParentDescribe<SUBJECT> extends Describe<SUBJECT> {

	public void describe(String desc, VoidBlock block) {
		new ChildDescribe<SUBJECT>(desc, block, this, report);
	}

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	void run() {
		its().forEach(it -> it.run());
	}

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

	ParentDescribe(Class<SUBJECT> clazz, ParentDescribeBlock<SUBJECT> block, Report report) {
		this(clazz.getName(), block, report);
	}

	ParentDescribe(String description, ParentDescribeBlock<SUBJECT> block, Report report) {
		super(description, report);
		perform(() -> block.eval(this));
	}
}
