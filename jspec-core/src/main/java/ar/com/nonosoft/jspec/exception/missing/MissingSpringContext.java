package ar.com.nonosoft.jspec.exception.missing;

import ar.com.nonosoft.jspec.exception.JSpecException;

public class MissingSpringContext extends JSpecException {
	public MissingSpringContext() {
		super("Missing spring context!");
	}
}
