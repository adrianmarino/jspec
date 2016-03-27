package ar.com.nonosoft.jspec.runner;

import ar.com.nonosoft.jspec.SpecSuite;

import static ar.com.nonosoft.jspec.util.StringUtils.NEWLINE;
import static ar.com.nonosoft.jspec.util.StringUtils.boldWithFbColor;
import static java.lang.System.out;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.fusesource.jansi.Ansi.Color.BLUE;

public class ConsoleRunner {

	public static void main(String[] args) {
		String packageName = packageName(args);
		out.print(newHeader(packageName).append(runSpecs(packageName)));
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private static String runSpecs(String packageName) {
		return new SpecSuite().addSpecsIn(packageName).run();
	}

	private static String packageName(String[] args) {
		return args.length > 0 ? args[0] : EMPTY;
	}

	private static StringBuilder newHeader(String packageName) {
		StringBuilder sb = new StringBuilder(NEWLINE).append(hStyle("Run specs"));

		if(!packageName.isEmpty()) sb.append(hStyle(" in ")).append(hStyle(packageName)).append(hStyle(" package"));

		return sb.append(hStyle("...")).append(NEWLINE).append(NEWLINE);
	}

	private static String hStyle(String value) {
		return boldWithFbColor(value, BLUE);
	}

}
