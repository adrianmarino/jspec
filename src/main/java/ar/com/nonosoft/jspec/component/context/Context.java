package ar.com.nonosoft.jspec.component.context;

import ar.com.nonosoft.jspec.component.Component;

import static ar.com.nonosoft.jspec.util.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public abstract class Context<SUBJECT> extends Component<Context<SUBJECT>, SUBJECT> {

	public Context(String description, Component parent) {
		super(description, parent);
	}

	public Context(String description) {
		super(description);
	}

	protected void printHeader() {
		output.println(withFgColor(capitalize(description()), DEFAULT)).beginLevel();
	}

	protected void printFooter() {
		output.endLevel();
	}
}
