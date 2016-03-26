package ar.com.nonosoft.jspec.exception.impl;

import ar.com.nonosoft.jspec.exception.MissingBlockException;

public class MissingLetException extends MissingBlockException {
	public MissingLetException(String block) {
		super(block, "let");
	}
}
