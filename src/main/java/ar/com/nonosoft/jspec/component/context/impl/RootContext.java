package ar.com.nonosoft.jspec.component.context.impl;

import ar.com.nonosoft.jspec.block.context.ContextBlock;
import ar.com.nonosoft.jspec.block.context.NestedContextBlock;
import ar.com.nonosoft.jspec.component.context.Context;
import ar.com.nonosoft.jspec.output.Report;

public class RootContext<SUBJECT> extends Context<SUBJECT> {
	public RootContext(String description, ContextBlock<SUBJECT> block, Report report) {
		super(description, report);
		perform(() -> block.eval(this));
	}

	public void context(String desc, NestedContextBlock block) {
		new NestedContext<SUBJECT>(desc, block, this, report);
	}
}
