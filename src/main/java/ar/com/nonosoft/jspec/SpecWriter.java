package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;

import java.util.ArrayList;
import java.util.List;

public class SpecWriter {

	private List<RootDescription> descriptions;

	public <SUBJECT> SpecWriter describe(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		descriptions.add(new RootDescription<>(clazz.getName(), block));
		return this;
	}

	List<RootDescription> getDescriptions() {
		return descriptions;
	}

	public SpecWriter() {
		descriptions = new ArrayList<>();
	}
}
