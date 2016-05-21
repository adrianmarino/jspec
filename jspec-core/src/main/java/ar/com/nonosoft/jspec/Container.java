package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.BlockExecutor;
import ar.com.nonosoft.jspec.block.ItBlock;
import ar.com.nonosoft.jspec.block.LetBlockManager;
import ar.com.nonosoft.jspec.context.SpringContext;
import ar.com.nonosoft.jspec.exception.missing.context.MissingSpringContextException;
import ar.com.nonosoft.jspec.exception.missing.block.MissingBlockException;
import ar.com.nonosoft.jspec.exception.missing.block.impl.MissingLetException;
import ar.com.nonosoft.jspec.exception.missing.block.impl.MissingSubjectException;
import ar.com.nonosoft.jspec.exception.missing.context.SpringContextException;
import ar.com.nonosoft.jspec.output.Report;
import ar.com.nonosoft.jspec.test.impl.It;
import ar.com.nonosoft.jspec.test.impl.Pending;
import ar.com.nonosoft.jspec.test.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static ar.com.nonosoft.jspec.util.collection.CollectionUtils.lookUp;
import static ar.com.nonosoft.jspec.util.collection.CollectionUtils.lookUpAndGet;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

@SuppressWarnings("unchecked")
public abstract class Container<COMPONENT, SUBJECT> {

    // --------------------------------------------------------------------------
    // Public Methods
    // --------------------------------------------------------------------------

    /**
     * Get a bean from a spring context configured using {@link #context(Class)} method.
     *
     * @param name bean name.
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
     * @param contextClass test context class.
     */
    public void springContext(Class contextClass) {
        context = new SpringContext(contextClass);
    }

    /**
     * Subject is a let with "subject" name. It's commonly used to assign the object specified (object to test).
     *
     * @param block return object to specify (to test).
     * @return spec
     * @see <a href="http://betterspecs.org/#subject">Use subject</a>
     */
    public COMPONENT subject(Supplier<SUBJECT> block) {
        return let(SUBJECT, block);
    }

    /**
     * Subject for constant values. A constant value is defined as a value that not depends to let to be built.
     * WARNING: Don't add values that depends to let. For achieve this behavior use a
     * default {@link #subject(Supplier) subject}.
     *
     * @param block return object to specify (to test).
     * @return spec
     * @see <a href="http://betterspecs.org/#subject">Use subject</a>
     */
    public COMPONENT subject(SUBJECT value) {
        return let(SUBJECT, value);
    }

    /**
     * Get a subject value
     *
     * @see <a href="http://betterspecs.org/#subject">Use subject</a>
     */
    public SUBJECT subject() {
        return letLockUpAndGet(SUBJECT, null, new MissingSubjectException());
    }

    /**
     * Use let when you have to assign a variable. Using let the variable lazy loads only when
     * it is used the first time in the test and block cached until that specific test is finished.
     *
     * @param name  variable name
     * @param block return a variable value
     * @return spec
     * @see <a href="http://betterspecs.org/#let">Use let</a>
     */
    public COMPONENT let(String name, Supplier block) {
        letBlockManager.put(name, block);
        return (COMPONENT) this;
    }

    /**
     * let for constant values. A constant value is defined as a value that not depends to another let to be built.
     * WARNING: Don't add values that depends to other let. For achieve this behavior
     * use a default let. See default {@link #let(String, Supplier) let} for more details.
     *
     * @param name  constant name
     * @param value constant value
     * @return spec
     * @see <a href="http://betterspecs.org/#let">Use let</a>
     */
    public COMPONENT let(String name, Object value) {
        letBlockManager.put(name, value);
        return (COMPONENT) this;
    }

    /**
     * Get let value by name.
     *
     * @param name variable name
     * @return value
     * @see <a href="http://betterspecs.org/#let">Use let</a>
     */
    public <T> T get(String name) {
        return get(name, null);
    }

    /**
     * Get let value by name.
     *
     * @param name  variable name
     * @param clazz class of variable. Used only when not is possible infer the variable generic type.
     * @return value
     * @see <a href="http://betterspecs.org/#let">Use let</a>
     */
    public <T> T get(String name, Class<T> clazz) {
        return letLockUpAndGet(name, clazz, new MissingLetException(name));
    }

    /**
     * A It block is the equivalent to a JUnit test case method.
     * User only one expect by it block.
     *
     * @see <a href="http://betterspecs.org">Better Specs</a>
     */
    public void it(String desc, ItBlock block) {
        tests.add(new It(desc, block, this, report));
    }

    /**
     * A pending It. Used to describe a test case before being written. Also used to disable its execution.
     * See {@link #it(String, ItBlock) It} for more details.
     */
    public void it(String desc) {
        tests.add(new Pending(desc, report));
    }

    /**
     * A pending It. Used to describe a test case before being written. Also used to disable its execution.
     * See {@link #it(String, ItBlock) It} for more details.
     */
    public void xit(String desc, ItBlock block) {
        tests.add(new Pending(desc, report));
    }

    public String toString() {
        return description;
    }

    public void cleanLetCache() {
        lookUp(this, (c) -> c.letBlockManager.clean(), (c) -> c.parent);
    }

    // --------------------------------------------------------------------------
    // Package Methods
    // --------------------------------------------------------------------------

    void perform(BlockExecutor executor) {
        printHeader();
        executor.eval();
        printFooter();
    }

    List<Test> tests() {
        return new ArrayList<Test>() {{
            addAll(tests);
            children.forEach(child -> addAll(child.tests()));
        }};
    }

    // --------------------------------------------------------------------------
    // Protected Methods
    // --------------------------------------------------------------------------

    protected void printHeader() {
        report.printHeader(description, DEFAULT);
    }

    protected void printFooter() {
        report.printFooter();
    }

    // --------------------------------------------------------------------------
    // Private Methods
    // --------------------------------------------------------------------------

    private <T> T letLockUpAndGet(String name, Class<T> clazz, MissingBlockException exception) {
        return (T) lookUpAndGet(this, (c) -> c.letBlockManager.block(name), (c) -> c.parent, Supplier.class, exception).get();
    }

    private SpringContext context() {
        return lookUpAndGet(this, (c) -> c.context, (c) -> c.parent, SpringContext.class, new MissingSpringContextException());
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

    protected final String description;

    protected final List<Container> children;

    private Container parent;

    protected final LetBlockManager letBlockManager;

    private final List<Test> tests;

    private SpringContext context;

    // --------------------------------------------------------------------------
    // Constructors
    // --------------------------------------------------------------------------

    Container(String description, Report report) {
        this(description, null, report);
    }

    Container(String description, Container parent, Report report) {
        tests = new ArrayList<>();
        children = new ArrayList<>();
        letBlockManager = new LetBlockManager();
        this.description = description;
        this.report = report;
        initializeParent(parent);
    }
}
