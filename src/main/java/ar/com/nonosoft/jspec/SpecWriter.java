package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;

public class SpecWriter {

	public <SUBJECT> SpecWriter describe(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		suite.descriptions().add(new RootDescription<>(clazz.getName(), block, suite.report()));
		return this;
	}

	private SpecSuite suite;

	public SpecWriter(SpecSuite suite) {
		this.suite = suite;
	}
}
