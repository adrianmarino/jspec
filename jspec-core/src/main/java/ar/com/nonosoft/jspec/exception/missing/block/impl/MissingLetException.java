package ar.com.nonosoft.jspec.exception.missing.block.impl;

import ar.com.nonosoft.jspec.exception.missing.block.MissingBlockException;

public class MissingLetException extends MissingBlockException {
	public MissingLetException(String block) {
		super(block, "let");
	}
}
