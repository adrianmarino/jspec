package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;
import ar.com.nonosoft.jspec.output.Report;

import java.util.ArrayList;
import java.util.List;

public abstract class Specification<SUBJECT> {

	public void describe(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		descriptions.add(new RootDescription<>(clazz.getName(), block, report));
	}

	public void run() {
		descriptions.forEach(RootDescription::run);
		System.out.println(report);
	}

	public void run(Report anOtherReport) {
		descriptions.forEach(RootDescription::run);
		anOtherReport.syncWith(report);
	}

	private Report report;

	private List<RootDescription> descriptions;

	public Specification() {
		descriptions = new ArrayList<>();
		report = new Report();
	}
}
