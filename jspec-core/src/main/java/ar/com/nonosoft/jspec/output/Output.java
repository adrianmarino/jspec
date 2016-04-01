package ar.com.nonosoft.jspec.output;

import ar.com.nonosoft.jspec.util.AssertionErrorUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.fusesource.jansi.Ansi;

import static ar.com.nonosoft.jspec.util.StringUtils.*;
import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.repeat;
import static org.fusesource.jansi.Ansi.Color.RED;

public class Output {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	public String toString() {
		return output.toString();
	}

	public Output printFail(String desc, AssertionError cause) {
		print(withFgColor(capitalize(desc), RED)).println(boldWithFbColor(" FAIL!", RED)).beginLevel(level + FAIL_LEVEL);
		AssertionErrorUtils.errorLines(cause).forEach(line -> printBoldMessage(line, RED));
		endLevel(level + FAIL_LEVEL);
		return this;
	}

	public Output printError(String description, Exception exception) {
		return println(new StringBuilder(withFgColor(capitalize(description), RED))
				.append(boldWithFbColor(" ERROR!", RED))
				.append(withFgColor(" (Error: ", RED))
				.append(withFgColor(capitalize(exception.getMessage()), RED))
				.append(withFgColor(")", RED))
				.toString());
	}

	public Output printMessage(String message, Ansi.Color color) {
		return println(withFgColor(message, color));
	}

	public Output printBoldMessage(String message, Ansi.Color color) {
		return println(boldWithFbColor(message, color));
	}

	public Output beginLevel() {
		return beginLevel(1);
	}

	public Output beginLevel(Integer number) {
		range(0, number).forEach(num -> level++);
		return this;
	}

	public Output endLevel() {
		return endLevel(1);
	}

	public Output endLevel(Integer number) {
		if (level > ZERO) range(0, number).forEach(num -> level--);
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

	public Output var(Long var) {
		return print(format("{%s}", var));
	}

	public Output var(Long var, Output to) {
		return var(var, to.toString());
	}

	public Output var(Long var, String to) {
		output.replaceAll(format("{%s}", var), to);
		return this;
	}

	public void syncWith(Output anOtherOutput) {
		output.append(anOtherOutput);
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private String addPrefix(String value) {
		return repeat(LEVEL, level) + value;
	}

	// --------------------------------------------------------------------------
	// Constants
	// --------------------------------------------------------------------------

	public static final int ZERO = 0;

	public static final String LEVEL = WHITESPACE + WHITESPACE;


	public static final int FAIL_LEVEL = 3;

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	public Integer level;

	private StrBuilder output;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Output() {
		this(ZERO);
	}

	public Output(Integer level) {
		this.level = level;
		this.output = new StrBuilder();
	}
}
