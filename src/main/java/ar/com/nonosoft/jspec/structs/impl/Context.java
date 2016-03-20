package ar.com.nonosoft.jspec.structs.impl;

import ar.com.nonosoft.jspec.blocks.ContextBlock;
import ar.com.nonosoft.jspec.structs.SpecComponent;

import static ar.com.nonosoft.jspec.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class Context<T> extends SpecComponent<T> {
	public Context(String description, ContextBlock block) {
		output.println(withFgColor(capitalize(description), DEFAULT)).beginLevel();
		block.eval(this);
		output.endLevel();
	}
}
