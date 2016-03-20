package ar.com.nonosoft.jspec.exception;

import ar.com.nonosoft.jspec.structs.SpecComponent;

public class JSpecMissingSubjectException extends RuntimeException {
	public JSpecMissingSubjectException(SpecComponent component) {
		super("Missing subject block into " + component.getClass().getSimpleName().toLowerCase() +
					" block (" + component.getDescription() + ").");
	}
}
