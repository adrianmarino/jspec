package ar.com.nonosoft.jspec.blocks;

import ar.com.nonosoft.jspec.structs.impl.Description;

public interface DescriptionBlock<T> {
    void eval(Description<T> description);
}