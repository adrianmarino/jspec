package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;

import java.util.ArrayList;
import java.util.List;

public class Specification<SUBJECT> {

	public void describe(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		descriptions.add(new RootDescription<>(clazz.getName(), block));
	}

  public void run() {
		descriptions.forEach(RootDescription::run);
	}

	private List<RootDescription> descriptions;

	public Specification() {
		descriptions = new ArrayList<>();
	}
}
