package ar.com.nonosoft.jspec.component.context.impl;

import ar.com.nonosoft.jspec.blocks.context.ContextBlock;
import ar.com.nonosoft.jspec.blocks.context.NestedContextBlock;
import ar.com.nonosoft.jspec.component.context.Context;

public class RootContext<SUBJECT> extends Context<SUBJECT> {
	public RootContext(String description, ContextBlock<SUBJECT> block) {
		super(description);
		perform(() -> block.eval(this));
	}

	public void context(String desc, NestedContextBlock block) {
		new NestedContext<SUBJECT>(desc, block, this);
	}
}
