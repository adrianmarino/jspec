package ar.com.nonosoft.jspec.output.report;

import ar.com.nonosoft.jspec.output.Output;

import static ar.com.nonosoft.jspec.util.StringUtils.boldWithFbColor;
import static java.lang.String.valueOf;
import static org.fusesource.jansi.Ansi.Color.BLUE;

public class Report {

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

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

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

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private long failures, errors;

	private Output output;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Report() {
		failures = errors = 0;
		output = new Output();
	}
}
