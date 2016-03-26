package ar.com.nonosoft.test.jspec.component;

import ar.com.nonosoft.test.jspec.It;
import ar.com.nonosoft.test.jspec.block.ItBlock;
import ar.com.nonosoft.test.jspec.block.LetBlock;
import ar.com.nonosoft.test.jspec.exception.JSpecException;
import ar.com.nonosoft.test.jspec.exception.MissingBlockException;
import ar.com.nonosoft.test.jspec.exception.impl.MissingLetException;
import ar.com.nonosoft.test.jspec.exception.impl.MissingSubjectException;
import ar.com.nonosoft.test.jspec.output.report.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Component<COMPONENT, SUBJECT>  {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

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

	public String description() {
		return description;
	}


	public void perform(BlockExecutor executor) {
		printHeader();
		try {
			executor.eval();
		} catch (JSpecException exception) {
			report.output().printError(description(), exception);
			report.incErrorCounter();
		}
		printFooter();
	}

	public String toString() {
		return description();
	}

	public void it(String desc, ItBlock block) {
		its.add(new It(desc, block, this, report));
	}

	public List<It> its() {
		return new ArrayList<It>() {{ addAll(its); children.forEach(child -> addAll(child.its())); }};
	}

	public void resetLets() {
		letBlocks = null;
	}

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	protected LetBlock letBlock(String name) {
		return letBlocks().get(name);
	}

	protected abstract void printHeader();

	protected abstract void printFooter();
	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private LetBlock letLockUp(String name, MissingBlockException exception) {
		Component component = this;
		LetBlock block = null;
		while(component != null && block == null) {
			block = component.letBlock(name);
			component = component.parent;
		}

		if (block == null) throw exception;
		return block;
	}

	private Map<String, LetBlock> letBlocks() {
		return letBlocks == null ? letBlocks = new HashMap<>() : letBlocks;
	}

	private void initializeParent(Component parent) {
		this.parent = parent;
		if(parent != null) parent.children.add(this);
	}


	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private static final String SUBJECT = "subject";

	protected Report report;

	protected String description;

	protected List<Component> children;

	private Component parent;

	private Map<String, LetBlock> letBlocks;

	private List<It> its;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Component(String description, Report report) {
		this(description, null, report);
	}

	public Component(String description, Component parent, Report report) {
		this.its = new ArrayList<>();
		this.children = new ArrayList<>();
		this.description = description;
		initializeParent(parent);
		this.report = report;
	}
}
