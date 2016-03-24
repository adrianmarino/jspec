package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.It;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;
import ar.com.nonosoft.jspec.output.report.Report;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(JSpecRunner.class)
public abstract class Specification<SUBJECT> {

	public void describe(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		descriptions.add(new RootDescription<>(clazz.getName(), block, report));
	}

	public List<It> its() {
		return descriptions.stream().map(d -> d.its()).reduce(new ArrayList<It>(), (acc,its) -> { acc.addAll(its); return acc; } );
	}

	public void run(Report anOtherReport) {
		its().forEach(it->it.run());
		anOtherReport.syncWith(report);
	}

	private Report report;

	private List<RootDescription> descriptions;

	public Specification() {
		descriptions = new ArrayList<>();
		report = new Report();
	}
}
