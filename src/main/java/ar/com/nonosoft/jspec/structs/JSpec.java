package ar.com.nonosoft.jspec.structs;

import ar.com.nonosoft.jspec.blocks.DescriptionBlock;
import ar.com.nonosoft.jspec.structs.impl.Description;

public class JSpec<T> {
	public JSpec(Class<T> clazz, DescriptionBlock<T> block) {
		new Description<T>(clazz.getName(), block);
	}
}
