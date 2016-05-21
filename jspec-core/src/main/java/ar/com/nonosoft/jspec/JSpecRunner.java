package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.test.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.List;

@SuppressWarnings("unchecked")
public class JSpecRunner extends ParentRunner<Test> {

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	@Override
	protected List<Test> getChildren() {
		return newInstanceOf(getSpecClass()).tests();
	}

	@Override
	protected Description describeChild(Test test) {
		return Description.createTestDescription(test.description(), "");
	}

	@Override
	protected void runChild(Test test, RunNotifier notifier) {
		runLeaf(test, describeChild(test), notifier);
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
