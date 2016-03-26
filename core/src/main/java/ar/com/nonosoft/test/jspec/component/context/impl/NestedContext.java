package ar.com.nonosoft.test.jspec.component.context.impl;

import ar.com.nonosoft.test.jspec.block.context.NestedContextBlock;
import ar.com.nonosoft.test.jspec.component.Component;
import ar.com.nonosoft.test.jspec.component.context.Context;
import ar.com.nonosoft.test.jspec.output.report.Report;

public class NestedContext<SUBJECT> extends Context<SUBJECT> {
	public NestedContext(String description, NestedContextBlock block, Component parent, Report report) {
		super(description, parent, report);
		perform(() -> block.eval());
	}
}
