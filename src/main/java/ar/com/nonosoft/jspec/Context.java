package ar.com.nonosoft.jspec;

import static ar.com.nonosoft.jspec.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class Context extends SpecComponent {
	public Context(String description, ContextBlock block) {
		output.println(withFgColor(capitalize(description), DEFAULT)).beginLevel();
		block.eval(this);
		output.endLevel();
	}
}
