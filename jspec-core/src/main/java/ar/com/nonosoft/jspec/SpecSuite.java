package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentDescribeBlock;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.output.Report;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
public class SpecSuite {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	public SpecSuite addSpecsIn(String packageName) {
		Reflections reflections = new Reflections(packageName);
		Iterator<Class<? extends Spec>> iterator = reflections.getSubTypesOf(Spec.class).iterator();
		while(iterator.hasNext())
			specifications.add((Class<Spec<?>>) iterator.next());
		return this;
	}

	public <SPEC extends Spec<?>> SpecSuite addSpec(Class<SPEC> specification) {
		specifications.add((Class<Spec<?>>) specification);
		return this;
	}

	public <SUBJECT> SpecSuite describe(Class<SUBJECT> clazz, ParentDescribeBlock<SUBJECT> block) {
		descriptions.add(new ParentDescribe<>(clazz.getName(), block, report));
		return this;
	}

	public String run() {
		if(!haveSpecs())
			report.specsNotFound();
		else {
			runSpecifications();
			descriptions.forEach(ParentDescribe::run);
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
		for(Class<Spec<?>> specification : specifications) {
			try {
				specification.newInstance().run(report);
			} catch (Exception e) {
				throw new JSpecException("Error when run " + specification.getName() + " specification.", e);
			}
		}
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private List<Class<Spec<?>>> specifications;

	private List<ParentDescribe> descriptions;

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
