package ar.com.nonosoft.jspec.component.description.impl;

import ar.com.nonosoft.jspec.block.describe.DescribeBlock;
import ar.com.nonosoft.jspec.block.describe.NestedDescribeBlock;
import ar.com.nonosoft.jspec.component.description.Description;
import ar.com.nonosoft.jspec.output.Report;

import static ar.com.nonosoft.jspec.util.StringUtils.boldWithFbColor;
import static org.fusesource.jansi.Ansi.Color.*;

public class RootDescription<SUBJECT> extends Description<SUBJECT> {

	private DescribeBlock<SUBJECT> block;

	public RootDescription(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block, Report report) {
		this(clazz.getName(), block, report);
	}

	public RootDescription(String description, DescribeBlock<SUBJECT> block, Report report) {
		super(description, report);
		this.block = block;
	}

	public void describe(String desc, NestedDescribeBlock block) {
		new NestedDescription<SUBJECT>(desc, block, this, report);
	}

	public void run() {
		perform(() -> block.eval(this));
	}

	protected void printFooter() {
		report.output().endLevel();
	}

	protected void printHeader() {
		report.output().println(boldWithFbColor(description(), YELLOW)).beginLevel();
	}
}
