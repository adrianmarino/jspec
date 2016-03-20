package ar.com.nonosoft.jspec.structs.impl;

import ar.com.nonosoft.jspec.blocks.context.ContextBlock;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.structs.Component;

import static ar.com.nonosoft.jspec.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class Context<T> extends Component<Context<T>, T> {
	public Context(String description, Component parent, ContextBlock block) {
		super(description, parent);
		output.println(withFgColor(capitalize(getDescription()), DEFAULT)).beginLevel();
		try {
			block.eval(this);
		} catch (JSpecException exception) {
			printException(getDescription(), exception);
		}
		output.endLevel();
	}
}
