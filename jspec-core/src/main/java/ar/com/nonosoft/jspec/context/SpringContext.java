package ar.com.nonosoft.jspec.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContext {

	@SuppressWarnings("unchecked")
	public <T> T bean(String name) {
		return (T) context.getBean(name);
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private ApplicationContext newContext(Class contextClass) {
		return new AnnotationConfigApplicationContext(contextClass);
	}

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private final ApplicationContext context;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public SpringContext(Class contextClass) {
		context = newContext(contextClass);
	}
}
