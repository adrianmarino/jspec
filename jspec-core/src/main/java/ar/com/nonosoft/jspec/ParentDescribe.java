package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentDescribeBlock;
import ar.com.nonosoft.jspec.output.report.Report;

public class ParentDescribe<SUBJECT> extends Describe<SUBJECT> {

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	void run() {
		its().forEach(it -> it.run());
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
