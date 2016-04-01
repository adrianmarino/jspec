package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.BlockExecutor;
import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.exception.MissingBlockException;
import ar.com.nonosoft.jspec.exception.impl.MissingLetException;
import ar.com.nonosoft.jspec.exception.impl.MissingSubjectException;
import ar.com.nonosoft.jspec.output.report.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public abstract class Container<COMPONENT, SUBJECT>  {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	public COMPONENT subject(Supplier<SUBJECT> block) {
		return let(SUBJECT, block);
	}

	public COMPONENT subject(SUBJECT value) {
		return subject(() -> value);
	}

	public SUBJECT subject() {
		Supplier block = letLockUp(SUBJECT, new MissingSubjectException());
		return (SUBJECT) block.get();
	}

	public COMPONENT let(String name, Supplier block) {
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
		Supplier block = letLockUp(name, new MissingLetException(name));
		return (T) block.get();
	}

	public void it(String desc, ItBlock block) {
		its.add(new It(desc, block, this, report));
	}

	public String toString() {
		return description();
	}

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	String description() {
		return description;
	}


	void perform(BlockExecutor executor) {
		printHeader();
		try {
			executor.eval();
		} catch (JSpecException exception) {
			report.output().printError(description(), exception);
			report.incErrorCounter();
		}
		printFooter();
	}

	List<It> its() {
		return new ArrayList<It>() {{ addAll(its); children.forEach(child -> addAll(child.its())); }};
	}

	void resetLets() {
		letBlocks = null;
	}

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	protected Supplier letBlock(String name) {
		return letBlocks().get(name);
	}

	protected abstract void printHeader();

	protected abstract void printFooter();

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private Supplier letLockUp(String name, MissingBlockException exception) {
		Container component = this;
		Supplier block = null;
		while(component != null && block == null) {
			block = component.letBlock(name);
			component = component.parent;
		}

		if (block == null) throw exception;
		return block;
	}

	private Map<String, Supplier> letBlocks() {
		return letBlocks == null ? letBlocks = new HashMap<>() : letBlocks;
	}

	private void initializeParent(Container parent) {
		this.parent = parent;
		if(parent != null) parent.children.add(this);
	}


	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private static final String SUBJECT = "subject";

	protected Report report;

	protected String description;

	protected List<Container> children;

	private Container parent;

	private Map<String, Supplier> letBlocks;

	private List<It> its;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Container(String description, Report report) {
		this(description, null, report);
	}

	public Container(String description, Container parent, Report report) {
		this.its = new ArrayList<>();
		this.children = new ArrayList<>();
		this.description = description;
		initializeParent(parent);
		this.report = report;
	}
}
