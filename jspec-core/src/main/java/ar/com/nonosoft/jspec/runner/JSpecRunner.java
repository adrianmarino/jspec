package ar.com.nonosoft.jspec.runner;

import ar.com.nonosoft.jspec.assertion.It;
import ar.com.nonosoft.jspec.Spec;
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

	private Class<Spec> getSpecClass() {
		return (Class<Spec>) getTestClass().getJavaClass();
	}

	private Spec newInstanceOf(Class<Spec> specClass) {
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
