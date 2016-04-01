package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentDescribeBlock;
import ar.com.nonosoft.jspec.output.Report;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public class ParentDescribe<SUBJECT> extends Describe<SUBJECT> {

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	void run() {
		its().forEach(It::run);
	}

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	protected void printHeader() {
		report.printBoldHeader(description, DEFAULT);
	}

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	ParentDescribe(Class<SUBJECT> clazz, ParentDescribeBlock<SUBJECT> block, Report report) {
		this(clazz.getName(), block, report);
	}

	ParentDescribe(String description, ParentDescribeBlock<SUBJECT> block, Report report) {
		super(description, report);
		perform(() -> block.eval(this));
	}
}
