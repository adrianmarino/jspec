package ar.com.nonosoft.test.jspec.runner;

import ar.com.nonosoft.test.jspec.SpecSuite;

import static ar.com.nonosoft.test.jspec.util.StringUtils.boldWithFbColor;
import static ar.com.nonosoft.test.jspec.util.StringUtils.withFgColor;
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
		return args.length == 0 ? "" : args[0];
	}

	private static StringBuilder newHeader(String packageName) {
		return new StringBuilder("\n").append(withFgColor("Run specs in", GREEN))
				.append(" ").append(boldWithFbColor(packageName, GREEN))
				.append(withFgColor(" package...", GREEN)).append("\n\n");
	}
}
