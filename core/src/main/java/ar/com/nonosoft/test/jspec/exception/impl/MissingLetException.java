package ar.com.nonosoft.test.jspec.exception.impl;

import ar.com.nonosoft.test.jspec.exception.MissingBlockException;

public class MissingLetException extends MissingBlockException {
	public MissingLetException(String block) {
		super(block, "let");
	}
}
