package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.describe.DescribeBlock;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.output.report.Report;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpecSuite {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	public SpecSuite addSpecsIn(String packageName) {
		Reflections reflections = new Reflections(packageName);
		Iterator<Class<? extends Specification>> iterator = reflections.getSubTypesOf(Specification.class).iterator();
		while(iterator.hasNext())
			specifications.add((Class<Specification<?>>) iterator.next());
		return this;
	}

	public <SPEC extends Specification<?>> SpecSuite addSpec(Class<SPEC> specification) {
		specifications.add((Class<Specification<?>>) specification);
		return this;
	}

	public <SUBJECT> SpecSuite describe(Class<SUBJECT> clazz, DescribeBlock<SUBJECT> block) {
		descriptions.add(new RootDescription<>(clazz.getName(), block, report));
		return this;
	}

	public String run() {
		if(!haveSpecs())
			report.specsNotFound();
		else {
			runSpecifications();
			descriptions.forEach(RootDescription::run);
		}
		return report.toString();
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private Boolean haveSpecs() {
		return !(specifications.isEmpty() && descriptions.isEmpty());
	}

	private void runSpecifications() {
		for(Class<Specification<?>> specification : specifications) {
			try {
				specification.newInstance().run(report);
			} catch (Exception e) {
				throw new JSpecException("Error when build " + specification.getName() + " specification.", e);
			}
		}
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private List<Class<Specification<?>>> specifications;

	private List<RootDescription> descriptions;

	private Report report;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public SpecSuite() {
		specifications = new ArrayList<>();
		descriptions = new ArrayList<>();
		report = new Report();
	}
}
