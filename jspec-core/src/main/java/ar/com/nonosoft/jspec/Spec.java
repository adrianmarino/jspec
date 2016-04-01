package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;
import ar.com.nonosoft.jspec.output.report.Report;
import ar.com.nonosoft.jspec.runner.JSpecRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static ar.com.nonosoft.jspec.util.ClassUtils.subclassGenericParamOf;

@RunWith(JSpecRunner.class)
@SuppressWarnings("unchecked")
public abstract class Spec<SUBJECT> {

	public void describe(DescribeBlock<SUBJECT> block) {
		descriptions.add(new RootDescription<>(subclassGenericParamOf(getClass()).getTypeName(), block, report));
	}

	public List<It> its() {
		return descriptions.stream().map(RootDescription::its)
				.reduce(new ArrayList<>(), (acc, its) -> {
					acc.addAll(its);
					return acc;
				});
	}

	public void run(Report anOtherReport) {
		its().forEach(It::run);
		anOtherReport.syncWith(report);
	}

	private Report report;

	private List<RootDescription> descriptions;

	public Spec() {
		descriptions = new ArrayList<>();
		report = new Report();
	}
}
