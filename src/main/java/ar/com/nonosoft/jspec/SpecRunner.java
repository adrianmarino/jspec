package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.blocks.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;

import java.util.ArrayList;
import java.util.List;

public class SpecRunner {

	private List<RootDescription> descriptions;

	public <SUBJECT> SpecRunner describe(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		descriptions.add(new RootDescription<>(clazz.getName(), block));
		return this;
	}

	public void run() {
		descriptions.forEach(RootDescription::run);
	}

	public SpecRunner() {
		descriptions = new ArrayList<>();
	}
}
