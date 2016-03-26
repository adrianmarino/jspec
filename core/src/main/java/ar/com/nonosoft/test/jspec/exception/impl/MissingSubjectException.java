package ar.com.nonosoft.test.jspec.exception.impl;

import ar.com.nonosoft.test.jspec.exception.MissingBlockException;

public class MissingSubjectException extends MissingBlockException {
	public MissingSubjectException() {
		super("subject");
	}
}
