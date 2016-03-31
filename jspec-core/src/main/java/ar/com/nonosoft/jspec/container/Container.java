package ar.com.nonosoft.jspec.container;

import ar.com.nonosoft.jspec.It;
import ar.com.nonosoft.jspec.block.LetBlock;
import ar.com.nonosoft.jspec.block.Block;
import ar.com.nonosoft.jspec.exception.JSpecException;
import ar.com.nonosoft.jspec.exception.MissingBlockException;
import ar.com.nonosoft.jspec.exception.impl.MissingLetException;
import ar.com.nonosoft.jspec.exception.impl.MissingSubjectException;
import ar.com.nonosoft.jspec.output.report.Report;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
public abstract class Container<SUBJECT> {

	// --------------------------------------------------------------------------
	// Public Methods
	// --------------------------------------------------------------------------

	public void run() {
		printHeader();
		try {
			block.eval();
		} catch (JSpecException exception) {
			report.output().printError(description(), exception);
			report.incErrorCounter();
		}
		report.output().endLevel();
	}

	public <T> void expectThat(T actual, Matcher<? super T> matcher) {
		assertThat(actual, matcher);
	}

	public <T> void expectThat(String reason, T actual, Matcher<? super T> matcher) {
		assertThat(reason, actual, matcher);
	}

	public Container<SUBJECT> subject(LetBlock<SUBJECT> block) {
		return let(SUBJECT, block);
	}

	public SUBJECT subject() {
		LetBlock block = letLockUp(SUBJECT, new MissingSubjectException());
		return (SUBJECT) block.eval();
	}

	public Container<SUBJECT> let(String name, LetBlock block) {
		letBlocks().put(name, block);
		return this;
	}

	public <T> T get(String name) {
		LetBlock block = letLockUp(name, new MissingLetException(name));
		return (T) block.eval();
	}

	public <T> T get(String name, Class<T> clazz) {
		return get(name);
	}

	public String description() {
		return description;
	}

	public String toString() {
		return description();
	}

	public void it(String desc, Block block) {
		its.add(new It(desc, block, this, report));
	}

	public List<It> its() {
		return new ArrayList<It>() {{
			addAll(its);
			children.forEach(child -> addAll(child.its()));
		}};
	}

	public void resetLets() {
		letBlocks = null;
	}

	// --------------------------------------------------------------------------
	// Protected Methods
	// --------------------------------------------------------------------------

	abstract protected void printHeader();

	protected LetBlock letBlock(String name) {
		return letBlocks().get(name);
	}

	// --------------------------------------------------------------------------
	// Private Methods
	// --------------------------------------------------------------------------

	private LetBlock letLockUp(String name, MissingBlockException exception) {
		Container component = this;
		LetBlock block = null;
		while (component != null && block == null) {
			block = component.letBlock(name);
			component = component.parent;
		}

		if (block == null) throw exception;
		return block;
	}

	private Map<String, LetBlock> letBlocks() {
		return letBlocks == null ? letBlocks = new HashMap<>() : letBlocks;
	}

	private void initializeParent(Container parent) {
		this.parent = parent;
		if (parent != null) parent.children.add(this);
	}


	// --------------------------------------------------------------------------
	// Attributes
	// --------------------------------------------------------------------------

	private static final String SUBJECT = "subject";

	protected final Report report;

	private final String description;

	private List<Container> children;

	private Container parent;

	private Map<String, LetBlock> letBlocks;

	private List<It> its;

	private Block block;

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	public Container(String description, Block block, Report report) {
		this(description, block, null, report);
	}

	public Container(String description, Block block, Container parent, Report report) {
		this.its = new ArrayList<>();
		this.children = new ArrayList<>();
		this.description = description;
		this.report = report;
		initializeParent(parent);
		this.block = block;
	}
}
