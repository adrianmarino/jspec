package ar.com.nonosoft.jspec.component.description.impl;

import ar.com.nonosoft.jspec.blocks.describe.DescribeBlock;
import ar.com.nonosoft.jspec.blocks.describe.NestedDescribeBlock;
import ar.com.nonosoft.jspec.component.description.Description;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static org.fusesource.jansi.Ansi.Color.*;

public class RootDescription<SUBJECT> extends Description<SUBJECT> {

	private DescribeBlock<SUBJECT> block;

	public RootDescription(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		this(clazz.getName(), block);
	}

	public RootDescription(String description, DescribeBlock<SUBJECT> block) {
		super(description);
		this.block = block;
	}

	public void describe(String desc, NestedDescribeBlock block) {
		new NestedDescription<SUBJECT>(desc, block, this);
	}

	public void run() {
		perform(() -> block.eval(this));
	}

	protected void printFooter() {
		output.endLevel();
		output.newline().println(boldWithFbColor(report.toString(), BLUE));
	}

	protected void printHeader() {
		output.println(boldWithFbColor(description(), YELLOW)).beginLevel();
	}
}
