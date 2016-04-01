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

	/**
	 * Subject is a let with "subject". it's commonly used to assign the object specified (to test).
	 *
	 * @param block
	 *          return object to specify (to test).
	 *
	 * @return spec
	 * @see <a href="http://betterspecs.org/#subject">Use subject</a>
	 */
	public COMPONENT subject(Supplier<SUBJECT> block) {
		return let(SUBJECT, block);
	}

	/**
	 * Subject is a let with "subject". it's commonly used to assign the object specified (to test).
	 *
	 * @param value
	 *          object to specify (to test).
	 *
	 * @return spec
	 * @see <a href="http://betterspecs.org/#subject">Use subject</a>
	 */
	public COMPONENT subject(SUBJECT value) {
		return subject(() -> value);
	}

	/**
	 * Get a subject value
	 *
	 * @see <a href="http://betterspecs.org/#subject">Use subject</a>
	 */
	public SUBJECT subject() {
		Supplier block = letLockUp(SUBJECT, new MissingSubjectException());
		return (SUBJECT) block.get();
	}

	/**
	 * Use let when you have to assign a variable. Using let the variable lazy loads only when
	 * it is used the first time in the test and get cached until that specific test is finished.
	 *
	 * @param name
	 *          variable name
	 * @param block
	 *          return a variable value
	 * @return spec
	 * @see <a href="http://betterspecs.org/#let">Use let</a>
	 */
	public COMPONENT let(String name, Supplier block) {
		letBlocks().put(name, block);
		return (COMPONENT)this;
	}

	/**
	 * Use let when you have to assign a variable. Using let the variable lazy loads only when
	 * it is used the first time in the test and get cached until that specific test is finished.
	 *
	 * @param name
	 *          variable name
	 * @param value
	 *          variable value
	 * @return spec
	 * @see <a href="http://betterspecs.org/#let">Use let</a>
	 */
	public COMPONENT let(String name, Object value) {
		return let(name, () -> value);
	}

	/**
	 * Get let value by name.
	 *
	 * @param name
	 *          variable name
	 * @return value
	 * @see <a href="http://betterspecs.org/#let">Use let</a>
	 */
	public <T> T get(String name) {
		return get(name, null);
	}

	/**
	 * Get let value by name.
	 *
	 * @param name
	 *          variable name
	 * @param clazz
	 *          class of variable. Used only when not is possible infer the variable generic type.
	 * @return value
	 * @see <a href="http://betterspecs.org/#let">Use let</a>
	 */
	public <T> T get(String name, Class<T> clazz) {
		Supplier block = letLockUp(name, new MissingLetException(name));
		return (T) block.get();
	}

	/**
	 * A It block is the equivalent to a JUnit test case method.
	 * User only one expect by it block.
	 *
	 * @see <a href="http://betterspecs.org">Better Specs</a>
	 */
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

	Container(String description, Report report) {
		this(description, null, report);
	}

	Container(String description, Container parent, Report report) {
		this.its = new ArrayList<>();
		this.children = new ArrayList<>();
		this.description = description;
		initializeParent(parent);
		this.report = report;
	}
}