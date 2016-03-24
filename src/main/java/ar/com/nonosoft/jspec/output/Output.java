package ar.com.nonosoft.jspec.output;

import static org.apache.commons.lang.StringUtils.repeat;

public class Output {

	private StringBuilder output;

	public Output beginLevel() {
		level++;
		return this;
	}

	public Output endLevel() {
		if (level > ZERO) level--;
		return this;
	}

	public Output newline() {
		println("");
		return this;
	}

	public Output println(String value) {
		output.append(addPrefix(value)).append(NEWLINE);
		return this;
	}

	public Output print(String value) {
		output.append(addPrefix(value));
		return this;
	}

	private String addPrefix(String value) {
		return repeat(LEVEL_TAB, level) + value;
	}

	public String toString() {
		return output.toString();
	}

	public void syncWith(Output anOtherOutput) {
		output.append(anOtherOutput);
	}

	public static final int ZERO = 0;

	public static final String NEWLINE = "\n";

	public static final String LEVEL_TAB = "  ";

	public Integer level;

	public Output() {
		level = ZERO;
		output = new StringBuilder();
	}
}
