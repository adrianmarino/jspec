package ar.com.nonosoft.jspec.exception.impl;

import ar.com.nonosoft.jspec.exception.MissingBlockException;

public class MissingLetException extends MissingBlockException {
	public MissingLetException(Class componentClass, String componentDesc, String blockName) {
		super(componentClass, componentDesc, blockName, "let");
	}
}
