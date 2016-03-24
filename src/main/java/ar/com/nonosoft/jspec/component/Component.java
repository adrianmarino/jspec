package ar.com.nonosoft.jspec.component;

import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.block.LetBlock;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.exception.MissingBlockException;
import ar.com.nonosoft.jspec.exception.impl.MissingLetException;
import ar.com.nonosoft.jspec.exception.impl.MissingSubjectException;
import ar.com.nonosoft.jspec.output.Report;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.GREEN;

public abstract class Component<COMPONENT, SUBJECT> {

	private static final String SUBJECT = "subject";

	public Report report;

	protected String description;

	private Component parent;

	private Map<String, LetBlock> letBlocks;

	public Component(String description, Report report) {
		this.description = description;
		this.report = report;
	}

	public Component(String description, Component parent, Report report) {
		this.description = description;
		this.parent = parent;
		this.report = report;
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
			report.printMessage(capitalize(desc), GREEN);
		} catch (AssertionError cause) {
			report.printFail(desc, cause);
		} catch (Exception exception) {
			report.printError(desc, exception);
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
			report.printError(description(), exception);
		}
		printFooter();
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
}
