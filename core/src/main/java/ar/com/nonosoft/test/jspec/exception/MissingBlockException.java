package ar.com.nonosoft.test.jspec.exception;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public abstract class MissingBlockException extends JSpecException {
	public MissingBlockException(String blockName, String blockType) {
		super(message(blockName, blockType));
	}

	public MissingBlockException(String blockType) {
		super(message("", blockType));
	}

	private static String message(String blockName, String blockType) {
		StringBuilder sb = new StringBuilder("Missing ");
		if (isNotBlank(blockName)) sb.append("'").append(blockName).append("' ");
		return sb.append(blockType).toString();
	}
}
