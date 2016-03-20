package ar.com.nonosoft.jspec.exception.impl;

import ar.com.nonosoft.jspec.exception.MissingBlockException;

public class MissingSubjectException extends MissingBlockException {
	public MissingSubjectException(Class componentClass, String componentDesc) {
		super(componentClass, componentDesc, "subject");
	}
}
