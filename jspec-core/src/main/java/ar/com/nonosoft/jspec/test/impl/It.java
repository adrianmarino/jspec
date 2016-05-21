package ar.com.nonosoft.jspec.test.impl;

import ar.com.nonosoft.jspec.Container;
import ar.com.nonosoft.jspec.Expect;
import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.output.Report;
import ar.com.nonosoft.jspec.test.Test;
import org.junit.runners.model.Statement;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.format;

public class It extends Test {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	@Override
	public void evaluate() throws Throwable {
		block.eval(new Expect());
	}

	public void run() {
		try {
			report.incTests();
			block.eval(new Expect());
			report.printSuccess(id, description);
			parent.cleanLetCache();
		} catch (AssertionError e) {
			report.printFail(id, description, e);
		} catch (Exception e) {
			report.printError(id, description, e);
		}
	}

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	@Override
	public String description() {
		return format("It %s", description);
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private ItBlock block;

	private Container parent;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public It(String description, ItBlock block, Container parent, Report report) {
		super(description, report);
		this.parent = parent;
		this.block = block;
	}
}
