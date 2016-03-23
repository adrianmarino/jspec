package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.blocks.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;

public class Spec<SUBJECT> {

	private RootDescription description;

	public void describe(DescribeBlock<SUBJECT> block) {
		description = new RootDescription<>(getClass().getName(), block);
	}

	public void run() {
		description.run();
	}
}
