package ar.com.nonosoft.jspec.structs.impl;

import ar.com.nonosoft.jspec.blocks.describe.DescribeBlock;
import ar.com.nonosoft.jspec.blocks.describe.NestedDescribeBlock;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.structs.SpecComponent;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static org.fusesource.jansi.Ansi.Color.*;

public class Description<T> extends SpecComponent<Description<T>, T> {

	public Description(String description, NestedDescribeBlock block) {
		super(description);
		printHeader(false);
		try {
			block.eval();
		} catch (JSpecException exception) {
			printException(getDescription(), exception);
		}
		printFooter(false);
	}

	public Description(String description, DescribeBlock block) {
		super(description);
		printHeader(true);
		try {
			block.eval(this);
		} catch (JSpecException exception) {
			printException(getDescription(), exception);
		}
		printFooter(true);
	}

	private void printFooter(boolean root) {
		output.endLevel();
		if (root) output.newline().println(boldWithFbColor(report.toString(), BLUE));
	}

	private void printHeader(boolean root) {
		output.println(boldWithFbColor(getDescription(), root ? YELLOW : DEFAULT)).beginLevel();
	}

	public void describe(String desc, NestedDescribeBlock block) {
		new Description<T>(desc, block);
	}
}
