package ar.com.nonosoft.jspec.structs;

import ar.com.nonosoft.jspec.blocks.ItBlock;
import ar.com.nonosoft.jspec.blocks.LetBlock;
import ar.com.nonosoft.jspec.blocks.SubjectBlock;
import ar.com.nonosoft.jspec.blocks.context.ContextBlock;
import ar.com.nonosoft.jspec.exception.impl.MissingLetException;
import ar.com.nonosoft.jspec.exception.impl.MissingSubjectException;
import ar.com.nonosoft.jspec.output.Output;
import ar.com.nonosoft.jspec.output.SuiteReport;
import ar.com.nonosoft.jspec.structs.impl.Context;
import org.fusesource.jansi.Ansi.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static ar.com.nonosoft.jspec.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;

public abstract class Component<THIS, SUBJECT> {

	protected static final SuiteReport report = new SuiteReport();

	protected static final Output output = new Output();

	private String description;

	private Component parent;

	private SubjectBlock<SUBJECT> subjectBlock;

	private Map<String, LetBlock> letBlocks;

	private SUBJECT subject;

	public Component(String description) {
		this(description, null);
	}

	public Component(String description, Component parent) {
		this.description = description;
		this.parent = parent;
	}

	public void context(String description, ContextBlock<SUBJECT> block) {
		new Context<SUBJECT>(description, this, block);
	}

	public THIS subject(SubjectBlock<SUBJECT> block) {
		subjectBlock = block;
		return (THIS)this;
	}

	public THIS subject(SUBJECT value) {
		subjectBlock = () -> value;
		return (THIS)this;
	}

	public SUBJECT subject() {
		if (subjectBlock == null) throw new MissingSubjectException();
		return subject == null ? subject = subjectBlock.eval() : subject;
	}

	public THIS let(String name, LetBlock block) {
		letBlocks().put(name, block);
		return (THIS)this;
	}

	public THIS let(String name, Object value) {
		letBlocks().put(name, () -> value);
		return (THIS)this;
	}

	public <T> T get(String name) {
		return get(name, null);
	}

	public <T> T get(String name, Class<T> clazz) {
		Component component = this;
		LetBlock block = null;

		while(component != null && block == null) {
			block = component.letBlock(name);
			component = component.parent();
		}

		if (block == null) throw new MissingLetException( name);

		return (T) block.eval();
	}

	public void it(String desc, ItBlock spec) {
		try {
			resetSubject();
			spec.eval(new Expect());
			resetLets();
			printMessage(capitalize(desc), GREEN);
		} catch (AssertionError cause) {
			printMessage(capitalize(desc), RED);
			printAssertionError(cause);
			report.fail();
		} catch (Exception exception) {
			printException(desc, exception);
		}
	}

	protected void printException(String description, Exception exception) {
		printMessage(capitalize(description), RED);
		printError(exception.getMessage());
		report.error();
	}

	protected LetBlock letBlock(String name) {
		return letBlocks().get(name);
	}

	protected Component parent() {
		return parent;
	}

	private Map<String, LetBlock> letBlocks() {
		return letBlocks == null ? letBlocks = new HashMap<>() : letBlocks;
	}

	private void resetSubject() {
		subject = null;
	}

	private void resetLets() {
		letBlocks = null;
	}

	private void printError(String message) {
		output.println(boldWithFbColor("ERROR: ", RED) + withFgColor(capitalize(message), RED));
	}

	private void printAssertionError(AssertionError cause) {
		Scanner scanner = new Scanner(cause.getMessage());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().replace("\n", "");
			if (!line.isEmpty()) printMessage(line, RED);
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
