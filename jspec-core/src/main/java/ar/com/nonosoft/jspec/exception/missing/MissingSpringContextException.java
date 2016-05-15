package ar.com.nonosoft.jspec.exception.missing;

import ar.com.nonosoft.jspec.exception.JSpecException;

public class MissingSpringContextException extends JSpecException {
	public MissingSpringContextException() {
		super("Missing spring context!");
	}
}
