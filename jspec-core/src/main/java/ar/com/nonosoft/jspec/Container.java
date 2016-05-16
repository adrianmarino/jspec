package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.BlockExecutor;
import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.context.SpringContext;
import ar.com.nonosoft.jspec.exception.missing.context.MissingSpringContextException;
import ar.com.nonosoft.jspec.exception.missing.block.MissingBlockException;
import ar.com.nonosoft.jspec.exception.missing.block.impl.MissingLetException;
import ar.com.nonosoft.jspec.exception.missing.block.impl.MissingSubjectException;
import ar.com.nonosoft.jspec.exception.missing.context.SpringContextException;
import ar.com.nonosoft.jspec.output.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;

@SuppressWarnings("unchecked")
public abstract class Container<COMPONENT, SUBJECT>  {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	/**
	 * Get a bean from a spring context configured using {@link #context(Class)} method.
	 *
	 * @param name
	 * 			bean name.
     * @return bean instance.
     */
	public <T> T bean(String name) {
		try {
			return context().bean(name);
		} catch (Exception exception) {
			throw new SpringContextException(exception);
		}
	}

	/**
	 * Setup a Spring IOC context. Used to add a spring context to JSpec test context.
	 *
	 * @param contextClass
	 * 			test context class.
     */
	public void context(Class contextClass) {
		context = new SpringContext(contextClass);
	}

	/**
	 * Subject is a let with "subject". it's commonly used to assign the object specified (object to test).
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
	 * Subject is a let with "subject". it's commonly used to assign the object specified (object to test).
	 *
	 * @param subject
	 *        object to specify (to test).
	 *
	 * @return spec
	 * @see <a href="http://betterspecs.org/#subject">Use subject</a>
	 */
	public COMPONENT subject(SUBJECT subject) {
		return let(SUBJECT, subject);
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
		letBlocks().put(name, () -> value);
		return (COMPONENT)this;
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
		return description;
	}

	// --------------------------------------------------------------------------
	// Package Methods
	// --------------------------------------------------------------------------

	void perform(BlockExecutor executor) {
		printHeader();
		executor.eval();
		printFooter();
	}

	List<It> its() {
		return new ArrayList<It>() {{ addAll(its); children.forEach(child -> addAll(child.its())); }};
	}

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	protected Supplier letBlock(String name) {
		return letBlocks().get(name);
	}

	protected void printHeader() {
		report.printHeader(description, DEFAULT);
	}

	protected void printFooter() {
		report.printFooter();
	}

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

	private SpringContext context() {
		if(context != null) return context;

		Container component = this;
		SpringContext result = null;
		while(component != null && result == null) {
			result = component.context;
			component = component.parent;
		}

		if (result == null) throw new MissingSpringContextException();
		context = result;
		return result;
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

	private SpringContext context;

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
		this.report = report;
		initializeParent(parent);
	}
}
