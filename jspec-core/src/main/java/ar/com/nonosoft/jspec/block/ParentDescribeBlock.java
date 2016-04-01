package ar.com.nonosoft.jspec.block;

import ar.com.nonosoft.jspec.container.describe.impl.ParentDescribe;

public interface ParentDescribeBlock<SUBJECT> {
    void eval(ParentDescribe<SUBJECT> description);
}