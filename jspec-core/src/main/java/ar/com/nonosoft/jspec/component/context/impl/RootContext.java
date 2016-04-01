package ar.com.nonosoft.jspec.component.context.impl;

import ar.com.nonosoft.jspec.component.context.Context;
import ar.com.nonosoft.jspec.block.ContextBlock;
import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.output.report.Report;

public class RootContext<SUBJECT> extends Context<SUBJECT> {
	public RootContext(String description, ContextBlock<SUBJECT> block, Component parent, Report report) {
		super(description, parent, report);
		perform(() -> block.eval(this));
	}

	public void context(String desc, VoidBlock block) {
		new NestedContext<SUBJECT>(desc, block, this, report);
	}
}
