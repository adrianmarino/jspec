package ar.com.nonosoft.jspec;

import static org.apache.commons.lang.StringUtils.capitalize;

public class Context extends SpecComponent {
	public Context(String description, ContextBlock block) {
		output.print(capitalize(description)).beginLevel();
		block.eval(this);
		output.endLevel();
	}
}
