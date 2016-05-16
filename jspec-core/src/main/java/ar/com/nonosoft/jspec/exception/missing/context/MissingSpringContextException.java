package ar.com.nonosoft.jspec.exception.missing.context;

import ar.com.nonosoft.jspec.exception.JSpecException;

public class MissingSpringContextException extends JSpecException {
	public MissingSpringContextException() {
		super("Missing spring context!");
	}
}
