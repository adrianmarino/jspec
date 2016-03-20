package ar.com.nonosoft.jspec.blocks.describe;

import ar.com.nonosoft.jspec.structs.impl.Description;

public interface DescribeBlock<T> {
    void eval(Description<T> description);
}