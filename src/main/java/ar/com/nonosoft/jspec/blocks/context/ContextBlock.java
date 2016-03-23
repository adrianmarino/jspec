package ar.com.nonosoft.jspec.blocks.context;

import ar.com.nonosoft.jspec.component.context.impl.RootContext;

public interface ContextBlock<SUBJECT> {
	void eval(RootContext<SUBJECT> context);
}
