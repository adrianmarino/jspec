package ar.com.nonosoft.jspec.exception.missing.block.impl;

import ar.com.nonosoft.jspec.exception.JSpecException;

import static java.lang.String.format;

public class MultipleRootDescriptions extends JSpecException {
	public MultipleRootDescriptions(Class specClass) {
		super(format("Multiple root descriptions on '%s' specification!. Use only on root description by specification.", specClass.getName()));
	}
}
