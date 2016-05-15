package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.output.Report;
import org.junit.runners.model.Statement;

import java.util.concurrent.atomic.AtomicLong;

class It extends Statement {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	@Override
	public void evaluate() throws Throwable {
		block.eval(new Expect());
	}

	public void run() {
		try {
			report.incTestCounter();
			block.eval(new Expect());
			report.printSuccess(id, description);
		} catch (AssertionError e) {
			report.printFail(id, description, e);
		} catch (Exception e) {
			report.printError(id, description, e);
		}
	}

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	String description() {
		return description;
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private String description;

	private ItBlock block;

	private Container parent;

	private Report report;

	private Long id;

	private static AtomicLong counter = new AtomicLong(0);

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public It(String description, ItBlock block, Container parent, Report report) {
		this.description = description;
		this.parent = parent;
		this.block = block;
		this.report = report;
		this.id = counter.incrementAndGet();
		this.report.addItVar(id);
	}
}
