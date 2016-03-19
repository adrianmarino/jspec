package ar.com.nonosoft.jspec;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.capitalize;

public abstract class SpecComponent {

	protected	static final SuiteReport report = new SuiteReport();

	protected	static final Output output = new Output();

	public void it(String desc, Specification spec) {
		try {
			output.print(capitalize(desc));
			spec.eval(new Expect());
			report.success();
		} catch (AssertionError cause) {
			output.print(format("FAIL: %s", capitalize(cause.getMessage())));
			report.fail();
		} catch (Throwable cause) {
			output.print(format("ERROR: %s", capitalize(cause.getMessage())));
			report.error();
		}
	}
}
