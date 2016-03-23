package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.component.description.impl.RootDescription;
import ar.com.nonosoft.jspec.exception.JSpecException;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ar.com.nonosoft.jspec.util.StringUtils.boldWithFbColor;
import static org.fusesource.jansi.Ansi.Color.BLUE;

public class SpecSuite {

	public SpecSuite addAllSpecsOn(String packageName) {
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

	public SpecSuite addWriter(SpecWriter writer) {
		descriptions.addAll(writer.getDescriptions());
		return this;
	}

	public void run() {
		runSpecifications();
		descriptions.forEach(RootDescription::run);
		Component.output.newline().println(boldWithFbColor(Component.report.toString(), BLUE));
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
