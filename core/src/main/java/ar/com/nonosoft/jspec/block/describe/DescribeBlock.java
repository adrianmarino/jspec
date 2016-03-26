package ar.com.nonosoft.jspec.block.describe;

import ar.com.nonosoft.jspec.component.description.impl.RootDescription;

public interface DescribeBlock<SUBJECT> {
    void eval(RootDescription<SUBJECT> description);
}