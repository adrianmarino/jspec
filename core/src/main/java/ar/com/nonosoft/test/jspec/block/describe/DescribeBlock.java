package ar.com.nonosoft.test.jspec.block.describe;

import ar.com.nonosoft.test.jspec.component.description.impl.RootDescription;

public interface DescribeBlock<SUBJECT> {
    void eval(RootDescription<SUBJECT> description);
}