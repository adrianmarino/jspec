package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.blocks.describe.DescribeBlock;
import ar.com.nonosoft.jspec.structs.impl.Description;

public class JSpec {
	public static <T> void describe(Class<T> clazz, DescribeBlock<T> block) {
		new Description<T>(clazz.getName(), block);
	}

	private JSpec() {}
}
