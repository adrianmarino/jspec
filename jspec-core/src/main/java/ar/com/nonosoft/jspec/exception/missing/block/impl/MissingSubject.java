package ar.com.nonosoft.jspec.exception.missing.block.impl;

import ar.com.nonosoft.jspec.exception.missing.block.MissingBlockException;

public class MissingSubject extends MissingBlockException {
	public MissingSubject() {
		super("subject");
	}
}
