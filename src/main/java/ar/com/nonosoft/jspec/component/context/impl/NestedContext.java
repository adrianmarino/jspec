package ar.com.nonosoft.jspec.component.context.impl;

import ar.com.nonosoft.jspec.blocks.context.NestedContextBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.component.context.Context;

public class NestedContext<SUBJECT> extends Context<SUBJECT> {
	public NestedContext(String description, NestedContextBlock block, Component parent) {
		super(description, parent);
		perform(() -> block.eval());
	}
}
