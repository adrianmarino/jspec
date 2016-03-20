package ar.com.nonosoft.jspec.structs;

import ar.com.nonosoft.jspec.blocks.ContextBlock;
import ar.com.nonosoft.jspec.blocks.ItBlock;
import ar.com.nonosoft.jspec.blocks.SubjectBlock;
import ar.com.nonosoft.jspec.exception.JSpecMissingSubjectException;
import ar.com.nonosoft.jspec.output.Output;
import ar.com.nonosoft.jspec.output.SuiteReport;
import ar.com.nonosoft.jspec.structs.impl.Context;
import org.fusesource.jansi.Ansi.Color;

import java.util.Scanner;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static ar.com.nonosoft.jspec.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;

public abstract class SpecComponent<T> {

	protected	static final SuiteReport report = new SuiteReport();

	protected	static final Output output = new Output();

	private String description;

	private SubjectBlock<T> subjectBlock;

	private T subject;

	public SpecComponent(String description) {
		this.description = description;
	}

	public void context(String description, ContextBlock<T> block) {
		new Context<T>(description, block);
	}

	public void subject(SubjectBlock<T> block) {
		subjectBlock = block;
	}

	public T subject() {
		if(subjectBlock == null) throw new JSpecMissingSubjectException(this);

		if(subject == null) {
			subject = subjectBlock.eval();
		}
		return subject;
	}

	public void it(String desc, ItBlock spec) {
		try {
			resetSubject();
			spec.eval(new Expect());
			printMessage(capitalize(desc), GREEN);
		} catch (AssertionError cause) {
			printMessage(capitalize(desc), RED);
			printAssertionError(cause);
			report.fail();
		} catch (Throwable cause) {
			printMessage(capitalize(desc), RED);
			printError(cause.getMessage());
			report.error();
		}
	}

	private void resetSubject() {
		subject = null;
	}

	private void printError(String message) {
		output.println(boldWithFbColor("ERROR: ", RED) + withFgColor(capitalize(message), RED));
	}

	private void printAssertionError(AssertionError cause) {
		Scanner scanner = new Scanner(cause.getMessage());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().replace("\n", "");
			if(!line.isEmpty()) printMessage(line, RED);
		}
		scanner.close();
	}

	private void printMessage(String message, Color color) {
		output.println(withFgColor(message, color));
	}

	public String getDescription() {
		return description;
	}
}
