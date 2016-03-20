package ar.com.nonosoft.jspec.structs.impl;

import ar.com.nonosoft.jspec.blocks.DescriptionBlock;
import ar.com.nonosoft.jspec.blocks.NestedDescriptionBlock;
import ar.com.nonosoft.jspec.structs.SpecComponent;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static org.fusesource.jansi.Ansi.Color.*;

public class Description<T> extends SpecComponent<T> {

	public Description(String description, NestedDescriptionBlock block) {
		super(description);
		printHeader(false);
		block.eval();
		printFooter(false);
	}

	public Description(String description, DescriptionBlock block) {
		super(description);
		printHeader(true);
		block.eval(this);
		printFooter(true);
	}

	private void printFooter(boolean root) {
		output.endLevel();
		if(root) output.newline().println(boldWithFbColor(report.toString(), BLUE));
	}

	private void printHeader(boolean root) {
		output.println(boldWithFbColor(getDescription(), root ? YELLOW : DEFAULT)).beginLevel();
	}

	public void describe(String desc, NestedDescriptionBlock block) {
		new Description<T>(desc, block);
	}
}
