package ar.com.nonosoft.jspec;
import org.fusesource.jansi.Ansi.Color;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static ar.com.nonosoft.jspec.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;

public abstract class SpecComponent {

	protected	static final SuiteReport report = new SuiteReport();

	protected	static final Output output = new Output();

	public void it(String desc, Specification spec) {
		try {
			spec.eval(new Expect());
			printIt(desc, GREEN);
		} catch (AssertionError cause) {
			printIt(desc, RED);
			printErrorDetail("FAIL", cause.getMessage());
			report.fail();
		} catch (Throwable cause) {
			printIt(desc, RED);
			printErrorDetail("ERROR", cause.getMessage());
			report.error();
		}
	}

	private void printIt(String desc, Color color) {
		output.println(withFgColor(capitalize(desc), color));
	}

	private void printErrorDetail(String type, String message) {
		output.println(boldWithFbColor(type + ": ", RED) + withFgColor(capitalize(message), RED));
	}
}
