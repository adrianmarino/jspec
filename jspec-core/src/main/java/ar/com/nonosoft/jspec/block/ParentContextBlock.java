package ar.com.nonosoft.jspec.block;

import ar.com.nonosoft.jspec.ParentContext;

public interface ParentContextBlock<SUBJECT> {
	void eval(ParentContext<SUBJECT> context);
}
