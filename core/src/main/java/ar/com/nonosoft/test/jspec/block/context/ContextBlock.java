package ar.com.nonosoft.test.jspec.block.context;

import ar.com.nonosoft.test.jspec.component.context.impl.RootContext;

public interface ContextBlock<SUBJECT> {
	void eval(RootContext<SUBJECT> context);
}
