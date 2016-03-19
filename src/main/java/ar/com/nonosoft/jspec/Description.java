package ar.com.nonosoft.jspec;

import static org.apache.commons.lang.StringUtils.capitalize;

public class Description extends SpecComponent {

	public Description(String description, DescriptionBlock block) {
		this(description, block, false);
	}

	public Description(String description, DescriptionBlock block, boolean root) {
		output.print(capitalize(description)).beginLevel();
		block.eval(this);
		output.endLevel();
		if(root) output.newline().print(report.toString());
	}

	public void describe(String desc, DescriptionBlock block) {
		new Description(desc, block);
	}

	public void context(String description, ContextBlock block) {
		new Context(description, block);
	}
}
