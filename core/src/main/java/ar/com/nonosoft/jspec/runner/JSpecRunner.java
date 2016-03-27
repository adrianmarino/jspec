package ar.com.nonosoft.jspec.runner;

import ar.com.nonosoft.jspec.It;
import ar.com.nonosoft.jspec.Specification;
import ar.com.nonosoft.jspec.exception.JSpecException;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.List;

@SuppressWarnings("unchecked")
public class JSpecRunner extends ParentRunner<It> {

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	@Override
	protected List<It> getChildren() {
		return newInstanceOf(getSpecClass()).its();
	}

	@Override
	protected Description describeChild(It it) {
		return Description.createTestDescription("It " + it.description(), "");
	}

	@Override
	protected void runChild(It it, RunNotifier notifier) {
		runLeaf(it, describeChild(it), notifier);
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private Class<Specification> getSpecClass() {
		return (Class<Specification>) getTestClass().getJavaClass();
	}

	private Specification newInstanceOf(Class<Specification> specClass) {
		try {
			return specClass.newInstance();
		} catch (Exception e) {
			throw new JSpecException("Error whe create an instance of " + getSpecClass().getName() + " specification class", e);
		}
	}

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public JSpecRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
	}
}
