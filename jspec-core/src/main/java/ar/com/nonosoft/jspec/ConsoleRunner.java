package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.output.Output;

import static java.lang.System.out;
import static org.apache.commons.lang.StringUtils.EMPTY;

public class ConsoleRunner {

	public static void main(String[] args) {
		String packageName = packageName(args);
		out.print(newHeader(packageName).add(runSpecs(packageName)));
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

	private static Output newHeader(String packageName) {
		Output out = new Output().boldBlue("Run specs");
		if(!packageName.isEmpty()) out.ws().boldBlue("in").ws().boldBlue(packageName).ws().boldBlue("package");
		return out.boldBlue("...").nl(2);
	}
}
