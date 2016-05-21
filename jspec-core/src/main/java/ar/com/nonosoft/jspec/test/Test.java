package ar.com.nonosoft.jspec.test;

import ar.com.nonosoft.jspec.output.Report;
import org.junit.runners.model.Statement;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Test extends Statement {

    // --------------------------------------------------------------------------
    // Public Methods
    // --------------------------------------------------------------------------

    public abstract void run();

    // --------------------------------------------------------------------------
    // Package Methods
    // --------------------------------------------------------------------------

    public abstract String description();

    // --------------------------------------------------------------------------
    // Attributes
    // --------------------------------------------------------------------------

    protected String description;

    protected Report report;

    protected Long id;

    public static AtomicLong counter = new AtomicLong(0);

    // --------------------------------------------------------------------------
    // Constructors
    // --------------------------------------------------------------------------

    public Test(String description, Report report) {
        this.description = description;
        this.report = report;
        this.id = counter.incrementAndGet();
        this.report.addItVar(id);
    }
}
