package ar.com.nonosoft.jspec;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class Description extends SpecComponent {

	public Description(String description, DescriptionBlock block) {
		this(description, block, false);
	}

	public Description(String description, DescriptionBlock block, boolean root) {
		printHeader(description);
		block.eval(this);
		printFooter(root);
	}

	private void printFooter(boolean root) {
		output.endLevel();
		if(root) output.newline().println(boldWithFbColor(report.toString(), BLUE));
	}

	private void printHeader(String description) {
		output.println(boldWithFbColor(capitalize(description), DEFAULT)).beginLevel();
	}

	public void describe(String desc, DescriptionBlock block) {
		new Description(desc, block);
	}

	public void context(String description, ContextBlock block) {
		new Context(description, block);
	}
}
