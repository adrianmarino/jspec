package ar.com.nonosoft.jspec.exception.missing.block.impl;

import ar.com.nonosoft.jspec.exception.missing.block.MissingBlockException;

public class MissingSubjectException extends MissingBlockException {
	public MissingSubjectException() {
		super("subject");
	}
}
