package ar.com.nonosoft.jspec;

import static java.lang.System.out;
import static org.apache.commons.lang.StringUtils.repeat;

public class Output {

	public static final int ZERO = 0;
	public static final String NEWLINE = "\n";

	public Integer level;

	public Output beginLevel() {
		level++;
		return this;
	}

	public Output endLevel() {
		if(level > ZERO) level--;
		return this;
	}

	public Output newline() {
		print(NEWLINE);
		return this;
	}

	public Output print(String value) {
		out.println(repeat("\t", level) + value);
		return this;
	}

	public Output() {
		level = ZERO;
	}
}
