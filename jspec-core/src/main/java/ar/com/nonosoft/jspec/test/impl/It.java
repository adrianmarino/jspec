package ar.com.nonosoft.jspec.test.impl;

import ar.com.nonosoft.jspec.Container;
import ar.com.nonosoft.jspec.Expect;
import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.output.Report;
import ar.com.nonosoft.jspec.test.Test;

import static java.lang.String.format;

public class It extends Test {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	@Override
	public void evaluate() throws Throwable {
        try {
            block.eval(new Expect());
        } finally {
            parent.cleanLetCache();
        }
	}

	public void run() {
		try {
			report.incTests();
			block.eval(new Expect());
			report.printSuccess(id, description);
		} catch (AssertionError e) {
			report.printFail(id, description, e);
		} catch (Exception e) {
			report.printError(id, description, e);
		} finally {
			parent.cleanLetCache();
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
