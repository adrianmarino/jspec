package ar.com.nonosoft.test.jspec.output.report;

import ar.com.nonosoft.test.jspec.output.Output;

import static ar.com.nonosoft.test.jspec.util.StringUtils.boldWithFbColor;
import static java.lang.String.valueOf;
import static org.fusesource.jansi.Ansi.Color.BLUE;

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
		return output.newline().println(footer()).toString();
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private String footer() {
		return boldWithFbColor(
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
		output = new Output();
	}
}
