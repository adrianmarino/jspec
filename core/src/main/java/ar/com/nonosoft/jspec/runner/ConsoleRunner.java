package ar.com.nonosoft.jspec.runner;

import ar.com.nonosoft.jspec.SpecSuite;

import static ar.com.nonosoft.jspec.util.StringUtils.*;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.fusesource.jansi.Ansi.Color.GREEN;

public class ConsoleRunner {

	public static void main(String[] args) {
		String packageName = packageName(args);

		StringBuilder output = newHeader(packageName);
		output.append(runSpecs(packageName));

		System.out.println(output);
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private static String runSpecs(String packageName) {
		return new SpecSuite().addSpecsIn(packageName).run();
	}

	private static String packageName(String[] args) {
		return args.length == 0 ? EMPTY : args[0];
	}

	private static StringBuilder newHeader(String packageName) {
		StringBuilder sb = new StringBuilder(NEWLINE).append(boldAndGreen("Run specs"));

		if(!packageName.isEmpty()) sb.append(WHITESPACE).append(boldAndGreen(packageName)).append(boldAndGreen(" package"));

		return sb.append(boldAndGreen("...")).append(NEWLINE).append(NEWLINE);
	}

	private static String boldAndGreen(String value) {
		return boldWithFbColor(value, GREEN);
	}

}
