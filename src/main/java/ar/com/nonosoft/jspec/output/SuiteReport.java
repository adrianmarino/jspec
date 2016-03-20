package ar.com.nonosoft.jspec.output;

import static java.lang.String.valueOf;

public class SuiteReport {

	private long failures, errors;

	public SuiteReport() {
		failures = errors = 0;
	}

	public void fail() {
		failures++;
	}

	public void error() {
		errors++;
	}

	public String toString() {
		return new StringBuilder(valueOf(failures))
				.append(" ")
				.append( failures == 1  ? "failure" : "failures")
				.append(", ")
				.append(errors)
				.append(errors == 1 ? "error" : " errors")
				.append("").toString();
	}
}
