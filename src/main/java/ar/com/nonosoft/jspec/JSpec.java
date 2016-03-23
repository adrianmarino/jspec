package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.blocks.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;

import java.util.ArrayList;
import java.util.List;

public class JSpec {

	private List<RootDescription> descriptions;

	public <SUBJECT> JSpec describe(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		descriptions.add(new RootDescription<>(clazz.getName(), block));
		return this;
	}

	public void run() {
		descriptions.forEach(RootDescription::run);
	}

	public JSpec() {
		descriptions = new ArrayList<>();
	}

	public static JSpec instance() {
		return instance == null ? instance = new JSpec() : instance;
	}

	private static JSpec instance = null;
}
