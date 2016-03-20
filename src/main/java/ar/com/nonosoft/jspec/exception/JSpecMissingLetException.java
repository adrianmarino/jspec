package ar.com.nonosoft.jspec.exception;

import ar.com.nonosoft.jspec.structs.SpecComponent;

public class JSpecMissingLetException extends RuntimeException {
	public <T> JSpecMissingLetException(SpecComponent<T> component) {
		super("Missing let block into " + component.getClass().getSimpleName().toLowerCase() +
				" block (" + component.getDescription() + ").");
	}
}
