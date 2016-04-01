package ar.com.nonosoft.jspec.block;

import ar.com.nonosoft.jspec.component.context.impl.RootContext;

public interface ContextBlock<SUBJECT> {
	void eval(RootContext<SUBJECT> context);
}
