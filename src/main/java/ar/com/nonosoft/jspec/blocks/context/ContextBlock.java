package ar.com.nonosoft.jspec.blocks.context;

import ar.com.nonosoft.jspec.structs.impl.Context;

public interface ContextBlock<T> {
	void eval(Context<T> context);
}
