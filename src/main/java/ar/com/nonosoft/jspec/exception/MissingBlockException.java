package ar.com.nonosoft.jspec.exception;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public abstract class MissingBlockException extends JSpecException {
	public MissingBlockException(Class componentClass, String componentDesc, String blockName, String blockType) {
		super(message(componentClass, componentDesc, blockName, blockType));
	}

	public MissingBlockException(Class componentClass, String componentDesc, String blockType) {
		super(message(componentClass, componentDesc, "", blockType));
	}

	private static String message(Class componentClass, String componentDesc, String blockName, String blockType) {
		StringBuilder sb = new StringBuilder("Missing ");
		if (isNotBlank(blockName)) sb.append("'").append(blockName).append("' ");
		return sb.append(blockType).append(" into '").append(componentDesc).append("' ")
				.append(componentClass.getSimpleName().toLowerCase()).append(".").toString();
	}
}
