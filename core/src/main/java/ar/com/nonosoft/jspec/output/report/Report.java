package ar.com.nonosoft.jspec.output.report;

import ar.com.nonosoft.jspec.output.Output;
import ar.com.nonosoft.jspec.util.StringUtils;

import static java.lang.String.valueOf;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class Report {

	public Output output() {
		return output;
	}

	public void intFailCounter() {
		failures++;
	}

	public void incErrorCounter() {
		errors++;
	}

	public void incTestCounter() {
		tests++;
	}

	public void syncWith(Report report) {
		output.syncWith(report.output());
		tests += report.tests;
		failures += report.failures;
		errors += report.errors;
	}

	public String toString() {
		return output.newline().endLevel().println(footer()).toString();
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private String footer() {
		return StringUtils.boldWithFbColor(
				new StringBuilder(valueOf(tests))
						.append(" ")
						.append(tests == 1 ? "test" : "tests")
						.append(", ")
						.append(valueOf(failures))
						.append(" ")
						.append(failures == 1 ? "failure" : "failures")
						.append(", ")
						.append(errors)
						.append(" ")
						.append(errors == 1 ? "incErrorCounter" : "errors")
						.append(".").toString(),
				BLUE
		);
	}

	public void specsNotFound() {
		output.printBoldMessage("Specs not found!", YELLOW);
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private long failures, errors, tests;

	private Output output;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Report() {
		failures = errors = tests = 0;
		output = new Output().beginLevel();
	}
}
