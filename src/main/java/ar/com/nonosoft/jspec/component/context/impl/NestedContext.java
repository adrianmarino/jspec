package ar.com.nonosoft.jspec.component.context.impl;

import ar.com.nonosoft.jspec.block.context.NestedContextBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.component.context.Context;
import ar.com.nonosoft.jspec.output.Report;

public class NestedContext<SUBJECT> extends Context<SUBJECT> {
	public NestedContext(String description, NestedContextBlock block, Component parent, Report report) {
		super(description, parent, report);
		perform(() -> block.eval());
	}
}
