package ar.com.nonosoft.jspec;

import org.fusesource.jansi.Ansi.Color;

import java.util.Scanner;

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
			printMessage(capitalize(desc), GREEN);
		} catch (AssertionError cause) {
			printMessage(capitalize(desc), RED);
			printAssertionError(cause);
			report.fail();
		} catch (Throwable cause) {
			printMessage(capitalize(desc), RED);
			printError(cause.getMessage());
			report.error();
		}
	}

	private void printError(String message) {
		output.println(boldWithFbColor("ERROR: ", RED) + withFgColor(capitalize(message), RED));
	}

	private void printAssertionError(AssertionError cause) {
		Scanner scanner = new Scanner(cause.getMessage());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().replace("\n", "");
			if(!line.isEmpty()) printMessage(line, RED);
		}
		scanner.close();
	}

	private void printMessage(String message, Color color) {
		output.println(withFgColor(message, color));
	}
}
