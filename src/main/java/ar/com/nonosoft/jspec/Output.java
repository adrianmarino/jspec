package ar.com.nonosoft.jspec;

import static org.apache.commons.lang.StringUtils.repeat;

public class Output {


	public Output beginLevel() {
		level++;
		return this;
	}

	public Output endLevel() {
		if (level > ZERO) level--;
		return this;
	}

	public Output newline() {
		println(NEWLINE);
		return this;
	}

	public Output println(String value) {
		System.out.println(addPrefix(value));
		return this;
	}

	public Output print(String value) {
		System.out.print(addPrefix(value));
		return this;
	}

	private String addPrefix(String value) {
		return repeat(LEVEL_TAB, level) + value;
	}

	public static final int ZERO = 0;

	public static final String NEWLINE = "\n";

	public static final String LEVEL_TAB = "  ";

	public Integer level;

	public Output() {
		level = ZERO;
	}
}
