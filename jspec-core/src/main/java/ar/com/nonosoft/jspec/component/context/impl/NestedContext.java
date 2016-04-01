package ar.com.nonosoft.jspec.component.context.impl;

import ar.com.nonosoft.jspec.component.context.Context;
import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.output.report.Report;

public class NestedContext<SUBJECT> extends Context<SUBJECT> {
	public NestedContext(String description, VoidBlock block, Component parent, Report report) {
		super(description, parent, report);
		perform(() -> block.eval());
	}
}
