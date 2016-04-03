package ar.com.nonosoft.jspec.exception.missing.block.impl;

import ar.com.nonosoft.jspec.exception.missing.block.MissingBlockException;

public class MissingLet extends MissingBlockException {
	public MissingLet(String block) {
		super(block, "let");
	}
}
