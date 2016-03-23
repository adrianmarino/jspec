package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.component.description.impl.RootDescription;
import ar.com.nonosoft.jspec.exception.JSpecException;

import java.util.ArrayList;
import java.util.List;

public class SpecSuite {

	public SpecSuite addSpec(Class specification) {
		specifications.add((Class<Specification<?>>) specification);
		return this;
	}

	public SpecSuite addWriter(SpecWriter writer) {
		descriptions.addAll(writer.getDescriptions());
		return this;
	}

	public void run() {
		runSpecifications();
		descriptions.forEach(RootDescription::run);
	}

	private void runSpecifications() {
		for(Class<Specification<?>> specification : specifications) {
			try {
				specification.newInstance().run();
			} catch (Exception e) {
				throw new JSpecException("Error when run " + specification.getName() + " specification.", e);
			}
		}
	}

	private List<Class<Specification<?>>> specifications;

	private List<RootDescription> descriptions;

	public SpecSuite() {
		specifications = new ArrayList<>();
		descriptions = new ArrayList<>();
	}
}
