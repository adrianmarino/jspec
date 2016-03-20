package ar.com.nonosoft.jspec;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class Description extends SpecComponent {

	public Description(String description, NestedDescriptionBlock block) {
		printHeader(description);
		block.eval();
		printFooter(false);
	}
	public Description(String description, DescriptionBlock block) {
		printHeader(description);
		block.eval(this);
		printFooter(true);
	}

	private void printFooter(boolean root) {
		output.endLevel();
		if(root) output.newline().println(boldWithFbColor(report.toString(), BLUE));
	}

	private void printHeader(String description) {
		output.println(boldWithFbColor(capitalize(description), DEFAULT)).beginLevel();
	}

	public void describe(String desc, NestedDescriptionBlock block) {
		new Description(desc, block);
	}
}
