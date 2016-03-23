package ar.com.nonosoft.jspec.component;

import ar.com.nonosoft.jspec.blocks.ItBlock;
import ar.com.nonosoft.jspec.blocks.LetBlock;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.exception.MissingBlockException;
import ar.com.nonosoft.jspec.exception.impl.MissingLetException;
import ar.com.nonosoft.jspec.exception.impl.MissingSubjectException;
import ar.com.nonosoft.jspec.output.Output;
import ar.com.nonosoft.jspec.output.SuiteReport;
import org.fusesource.jansi.Ansi.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static ar.com.nonosoft.jspec.StringUtils.boldWithFbColor;
import static ar.com.nonosoft.jspec.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;

public abstract class Component<COMPONENT, SUBJECT> {

	private static final String SUBJECT = "subject";

	protected static final SuiteReport report = new SuiteReport();

	protected static final Output output = new Output();

	protected String description;

	private Component parent;

	private Map<String, LetBlock> letBlocks;

	public Component(String description) {
		this.description = description;
	}

	public Component(String description, Component parent) {
		this.description = description;
		this.parent = parent;
	}

	public COMPONENT subject(LetBlock<SUBJECT> block) {
		return let(SUBJECT, block);
	}

	public COMPONENT subject(SUBJECT value) {
		return subject(() -> value);
	}

	public SUBJECT subject() {
		LetBlock block = letLockUp(SUBJECT, new MissingSubjectException());
		return (SUBJECT) block.eval();
	}

	public COMPONENT let(String name, LetBlock block) {
		letBlocks().put(name, block);
		return (COMPONENT)this;
	}

	public COMPONENT let(String name, Object value) {
		return let(name, () -> value);
	}

	public <T> T get(String name) {
		return get(name, null);
	}

	public <T> T get(String name, Class<T> clazz) {
		LetBlock block = letLockUp(name, new MissingLetException(name));
		return (T) block.eval();
	}

	public void it(String desc, ItBlock spec) {
		try {
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

	public String description() {
		return description;
	}

	public void perform(BlockExecutor executor) {
		printHeader();
		try {
			executor.eval();
		} catch (JSpecException exception) {
			printException(description(), exception);
		}
		printFooter();
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

	protected abstract void printHeader();

	protected abstract void printFooter();

	private LetBlock letLockUp(String name, MissingBlockException exception) {
		Component component = this;
		LetBlock block = null;
		while(component != null && block == null) {
			block = component.letBlock(name);
			component = component.parent();
		}

		if (block == null) throw exception;
		return block;
	}

	private Map<String, LetBlock> letBlocks() {
		return letBlocks == null ? letBlocks = new HashMap<>() : letBlocks;
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
}
