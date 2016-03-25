package ar.com.nonosoft.jspec.exception.impl;

import ar.com.nonosoft.jspec.exception.MissingBlockException;

public class MissingSubjectException extends MissingBlockException {
	public MissingSubjectException() {
		super("subject");
	}
}
