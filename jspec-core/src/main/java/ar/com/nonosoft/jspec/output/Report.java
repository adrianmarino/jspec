package ar.com.nonosoft.jspec.output;

import org.fusesource.jansi.Ansi.Color;

import static ar.com.nonosoft.jspec.util.AssertionErrorUtils.errorLines;
import static ar.com.nonosoft.jspec.util.StringUtils.boldWithFbColor;
import static java.lang.String.valueOf;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class Report {

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
		output.syncWith(report.output);
		tests += report.tests;
		failures += report.failures;
		errors += report.errors;
	}

	public String toString() {
		return output.nl().addln(footer()).toString();
	}

	public void printSuccess(Long itId, String desc) {
		output.var(itId, new Output().capGreen(desc).nl());
	}

	public void printFail(Long itId, String desc, AssertionError cause) {
		Output fail = new Output().capRed(desc).ws().boldRed("FAIL!").ws();
		errorLines(cause).forEach(line -> fail.boldRed(line).ws());
		output.var(itId, fail.nl());
		intFailCounter();
	}

	public void printError(Long itId, String desc, Exception exception) {
		output.var(
				itId,
				new Output().capRed(desc).ws().boldRed("ERROR!").ws().capRed(exception.getMessage()).nl()
		);
		incErrorCounter();
	}

	public void addItVar(Long id) {
		output.var(id);
	}

	public void printHeader(String desc, Color color) {
		output.addCap(desc, color).nl().beginLevel();
	}

	public void printBoldHeader(String desc, Color color) {
		output.capBold(desc, color).nl().beginLevel();
	}

	public void printFooter() {
		output.endLevel();
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private String footer() {
		return boldWithFbColor(
				new Output(valueOf(tests)).ws()
						.add(tests == 1 ? "test" : "tests")
						.add(",").ws()
						.add(valueOf(failures)).ws()
						.add(failures == 1 ? "failure" : "failures")
						.add(",").ws()
						.add(errors).ws()
						.add(errors == 1 ? "incErrorCounter" : "errors")
						.add(".").toString(),
				BLUE
		);
	}

	public void specsNotFound() {
		output.boldln("Specs not found!", YELLOW);
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
