package ar.com.nonosoft.jspec.component.description.impl;

import ar.com.nonosoft.jspec.blocks.describe.NestedDescribeBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.component.description.Description;
import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class NestedDescription<SUBJECT> extends Description<SUBJECT> {

	public NestedDescription(String description, NestedDescribeBlock block, Component parent) {
		super(description, parent);
		block.eval();
	}

	protected void printFooter() {
		output.endLevel();
	}

	protected void printHeader() {
		output.println(boldWithFbColor(description(), DEFAULT)).beginLevel();
	}
}
