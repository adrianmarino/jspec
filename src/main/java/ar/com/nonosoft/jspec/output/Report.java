package ar.com.nonosoft.jspec.output;

import org.fusesource.jansi.Ansi.Color;

import java.util.Scanner;

import static ar.com.nonosoft.jspec.util.StringUtils.boldWithFbColor;
import static ar.com.nonosoft.jspec.util.StringUtils.withFgColor;
import static java.lang.String.valueOf;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.RED;

public class Report {

	public void printFail(String desc, AssertionError cause) {
		printMessage(capitalize(desc), RED);
		printAssertionError(cause);
		fail();
	}

	public void printError(String description, Exception exception) {
		printMessage(capitalize(description), RED);
		printError(exception.getMessage());
		error();
	}

	public void printAssertionError(AssertionError cause) {
		Scanner scanner = new Scanner(cause.getMessage());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().replace("\n", "");
			if (!line.isEmpty()) printMessage(line, RED);
		}
		scanner.close();
	}

	public void printMessage(String message, Color color) {
		output.println(withFgColor(message, color));
	}

	public void printError(String message) {
		output.println(boldWithFbColor("ERROR: ", RED) + withFgColor(capitalize(message), RED));
	}

	public Output output() {
		return output;
	}

	public void fail() {
		failures++;
	}

	public void error() {
		errors++;
	}

	public void syncWith(Report report) {
		output.syncWith(report.output());
		failures += report.failures;
		errors += report.errors;
	}

	public String toString() {
		return output.newline().println(footer()).toString();
	}

	private String footer() {
		return boldWithFbColor(
				new StringBuilder(valueOf(failures))
					.append(" ")
					.append(failures == 1 ? "failure" : "failures")
					.append(", ")
					.append(errors)
					.append(" ")
					.append(errors == 1 ? "error" : "errors")
					.append(".").toString(),
				BLUE
		);
	}

	private long failures, errors;

	private Output output;

	public Report() {
		failures = errors = 0;
		output = new Output();
	}
}
