package ar.com.nonosoft.jspec.output;

import org.apache.commons.lang.text.StrBuilder;
import org.fusesource.jansi.Ansi.Color;

import static ar.com.nonosoft.jspec.util.StringUtils.*;
import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.repeat;
import static org.fusesource.jansi.Ansi.Color.*;

public class Output {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	public String toString() {
		return output.toString();
	}

	public Output boldln(Object value, Color color) {
		return addln(boldWithFbColor(value.toString(), color));
	}

	public Output boldBlue(Object value) {
		return add(boldWithFbColor(value.toString(), BLUE));
	}

	public Output boldRed(Object value) {
		return add(boldWithFbColor(value.toString(), RED));
	}

	public Output capBold(Object value, Color color) {
		return bold(capitalize(value.toString()), color);
	}

	public Output bold(Object value, Color color) {
		return add(boldWithFbColor(value.toString(), color));
	}

	public Output addCap(Object value, Color color) {
		return add(capitalize(value.toString()), color);
	}

	public Output add(Object value, Color color) {
		return add(withFgColor(value.toString(), color));
	}

	public Output addln(Object value, Color color) {
		return addln(withFgColor(value.toString(), color));
	}

	public Output addln(Object value) {
		output.append(addPrefix(value.toString())).append(NEWLINE);
		return this;
	}

	public Output capGreen(Object value) {
		return green(capitalize(value.toString()));
	}

	public Output green(Object value) {
		return add(value, GREEN);
	}


	public Output capRed(Object value) {
		return red(capitalize(value.toString()));
	}

	public Output red(Object value) {
		return add(value, RED);
	}

	public Output addCap(Object value) {
		return add(capitalize(value.toString()));
	}

	public Output add(Object value) {
		output.append(addPrefix(value.toString()));
		return this;
	}
	
	public Output ws() {
		return add(WHITESPACE);
	}

	public Output var(Long var) {
		return add(format("{%s}", var));
	}

	public Output var(Long var, Object to) {
		output.replaceAll(format("{%s}", var), to.toString());
		return this;
	}

	public void syncWith(Output anOtherOutput) {
		anOtherOutput.level = level;
		output.append(anOtherOutput.output);
	}


	public Output beginLevel() {
		return beginLevel(1);
	}

	public Output beginLevel(Integer number) {
		range(ZERO, number).forEach(num -> level++);
		return this;
	}

	public Output endLevel() {
		return endLevel(1);
	}

	public Output endLevel(Integer number) {
		if (level > ZERO) range(ZERO, number).forEach(num -> level--);
		return this;
	}

	public Output nl(Integer times) {
		return add(repeat(NEWLINE, times));
	}

	public Output nl() {
		return nl(1);
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

	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	public Integer level;

	private StrBuilder output;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Output() {
		this(ZERO, EMPTY);
	}

	public Output(Object value) {
		this(ZERO, value);
	}

	public Output(Integer level) {
		this(level, EMPTY);
	}

	public Output(Integer level, Object value) {
		this.level = level;
		this.output = new StrBuilder(value.toString()) ;
	}
}
