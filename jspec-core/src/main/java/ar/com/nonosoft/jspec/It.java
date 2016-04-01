package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.output.Output;
import ar.com.nonosoft.jspec.output.report.Report;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runners.model.Statement;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.GREEN;

class It extends Statement {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	@Override
	public void evaluate() throws Throwable {
		try {
			block.eval(new Expect());
		} catch (AssertionError cause) {
			throw new AssumptionViolatedException(cause.getMessage());
		}
	}

	public void run() {
		report.incTestCounter();
		try {
			block.eval(new Expect());
			parent.resetLets();
			report.output().var(id, new Output().printMessage(capitalize(description), GREEN));
		} catch (AssertionError cause) {
			report.output().var(id, new Output().printFail(description, cause));
			report.intFailCounter();
		} catch (Exception exception) {
			report.output().var(id, new Output().printError(description, exception));
			report.incErrorCounter();
		}
	}

	public String description() {
		return description;
	}

	public String toString() {
		return format("It %s", description());
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private String description;

	private ItBlock block;

	private Container parent;

	private Report report;

	private Long id;

	private static transient long counter = 0;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public It(String description, ItBlock block, Container parent, Report report) {
		this.description = description;
		this.parent = parent;
		this.block = block;
		this.report = report;
		this.id = ++counter;
		this.report.output().var(id);
	}

}
