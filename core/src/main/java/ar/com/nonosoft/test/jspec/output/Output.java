package ar.com.nonosoft.test.jspec.output;

import ar.com.nonosoft.test.jspec.util.AssertionErrorUtils;
import ar.com.nonosoft.test.jspec.util.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.fusesource.jansi.Ansi;

import java.util.stream.IntStream;

import static java.lang.String.format;
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
		print(StringUtils.withFgColor(capitalize(desc), RED)).println(StringUtils.boldWithFbColor(" FAIL!", RED)).beginLevel(FAIL_LEVEL);
		AssertionErrorUtils.errorLines(cause).forEach(line -> printBoldMessage(line, RED));
		endLevel(FAIL_LEVEL);
		return this;
	}

	public Output printError(String description, Exception exception) {
		return println(new StringBuilder(StringUtils.withFgColor(capitalize(description), RED))
				.append(StringUtils.boldWithFbColor(" ERROR!", RED))
				.append(StringUtils.withFgColor(" (Error: ", RED))
				.append(StringUtils.withFgColor(capitalize(exception.getMessage()), RED))
				.append(StringUtils.withFgColor(")", RED))
				.toString());
	}

	public Output printMessage(String message, Ansi.Color color) {
		return println(StringUtils.withFgColor(message, color));
	}

	public Output printBoldMessage(String message, Ansi.Color color) {
		return println(StringUtils.boldWithFbColor(message, color));
	}

	public Output beginLevel() {
		return beginLevel(1);
	}

	public Output beginLevel(Integer number) {
		IntStream.range(0, number).forEach(num -> level++);
		return this;
	}

	public Output endLevel() {
		return endLevel(1);
	}

	public Output endLevel(Integer number) {
		if (level > ZERO) IntStream.range(0, number).forEach(num -> level--);
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

	public static final String NEWLINE = "\n";

	public static final String LEVEL = "  ";

	public static final int FAIL_LEVEL = 2;

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
