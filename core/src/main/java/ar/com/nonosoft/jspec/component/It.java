package ar.com.nonosoft.jspec.component;

import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.output.Output;
import ar.com.nonosoft.jspec.output.report.Report;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.GREEN;

public class It extends Statement {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	@Override
	public void evaluate() throws Throwable {
		block.eval(new Expect());
	}

	public void run() {
		try {
			block.eval(new Expect());
			parent.resetLets();
			report.output().var(id, new Output().printMessage(capitalize(description), GREEN));
		} catch (AssertionError cause) {
			report.output().var(id, new Output().printFail(description, cause));
			report.fail();
		} catch (Exception exception) {
			report.output().var(id, new Output().printError(description, exception));
			report.error();
		}
	}

	public String description() {
		return description;
	}

	public String toString() {
		return format("It %s", description());
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private List<String> assertLines(AssertionError error) {
		List<String> lines= new ArrayList<>();
		Scanner scanner = new Scanner(error.getMessage());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().replace("\n", "");
			if (!line.isEmpty()) lines.add(line);
		}

		return lines;
	}


	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private String description;

	private ItBlock block;

	private Component parent;

	private Report report;

	private Long id;

	private static transient long counter = 0;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public It(String description, ItBlock block, Component parent, Report report) {
		this.description = description;
		this.parent = parent;
		this.block = block;
		this.report = report;
		this.id = ++counter;
		this.report.output().var(id);
	}

}
