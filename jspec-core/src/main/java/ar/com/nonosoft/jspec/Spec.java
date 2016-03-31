package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.Block;
import ar.com.nonosoft.jspec.block.LetBlock;
import ar.com.nonosoft.jspec.container.Container;
import ar.com.nonosoft.jspec.container.impl.Context;
import ar.com.nonosoft.jspec.container.impl.Describe;
import ar.com.nonosoft.jspec.output.report.Report;
import ar.com.nonosoft.jspec.runner.JSpecRunner;
import org.hamcrest.Matcher;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static ar.com.nonosoft.jspec.util.ClassUtils.subclassGenericParamOf;
import static org.junit.Assert.assertThat;

@RunWith(JSpecRunner.class)
@SuppressWarnings("unchecked")
public abstract class Spec<SUBJECT> {

	//
	// Containers
	//

	public void describe(String desc, Block block) {
		containers.add(new Describe<>(desc, block, report));
	}

	public void context(String desc, Block block) {
		containers.add(new Context<>(desc, block, report));
	}

	//
	// Context state
	//

	public Spec<SUBJECT> subject(LetBlock<SUBJECT> block) {
		currentContainer.subject(block);
		return this;
	}

	public Spec<SUBJECT> subject(SUBJECT value) {
		return subject(() -> value);
	}

	public SUBJECT subject() {
		return currentContainer.subject();
	}

	public Spec<SUBJECT> let(String name, LetBlock block) {
		currentContainer.let(name, block);
		return this;
	}

	public Spec<SUBJECT> let(String name, Object value) {
		return let(name, () -> value);
	}

	public <T> T get(String name) {
		return (T) currentContainer.get(name);
	}

	public <T> T get(String name, Class<T> clazz) {
		return get(name);
	}

	//
	// Assertion
	//

	public void it(String desc, Block block) {
		currentContainer.it(desc, block);
	}

	public <T> void expectThat(T actual, Matcher<? super T> matcher) {
		assertThat(actual, matcher);
	}

	public <T> void expectThat(String reason, T actual, Matcher<? super T> matcher) {
		assertThat(reason, actual, matcher);
	}

	//
	// Internal methods
	//

	public List<It> its() {
		return containers.stream().map(d -> d.its()).reduce(new ArrayList<It>(), (acc, its) -> {
			acc.addAll(its);
			return acc;
		});
	}

	public void run(Report anOtherReport) {
		its().forEach(It::run);
		anOtherReport.syncWith(report);
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private void add(Container<SUBJECT> container) {
		containers.add(container);
		currentContainer = container;
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private Report report;

	private List<Container<SUBJECT>> containers;

	private Container<SUBJECT> currentContainer;

	protected final Spec<SUBJECT> spec;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Spec() {
		containers = new ArrayList<>();
		report = new Report();
		add(new Describe<>(subclassGenericParamOf(getClass()).getTypeName(), report));
		spec = this;
	}
}
