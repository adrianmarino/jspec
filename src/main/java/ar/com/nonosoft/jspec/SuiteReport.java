package ar.com.nonosoft.jspec;

public class SuiteReport {

	private long successful, failures, errors;

	public SuiteReport() {
		successful = failures = errors = 0;
	}

	public void success() {
		successful++;
	}

	public void fail() {
		failures++;
	}

	public void error() {
		errors++;
	}

	public String toString() {
		return new StringBuilder("Totals: ")
				.append(successful).append(" successful, ")
				.append(failures).append(" failures, ")
				.append(errors).append(" errors.")
				.toString();
	}
}
