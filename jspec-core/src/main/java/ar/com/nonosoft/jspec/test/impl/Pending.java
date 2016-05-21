package ar.com.nonosoft.jspec.test.impl;
import ar.com.nonosoft.jspec.output.Report;
import ar.com.nonosoft.jspec.test.Test;

import static java.lang.String.format;

public class Pending extends Test {

    // --------------------------------------------------------------------------
    // Public Methods
    // --------------------------------------------------------------------------

    @Override
    public void evaluate() throws Throwable {
    }

    public void run() {
        report.incPendings();
        report.printPending(id, description);
    }

    // --------------------------------------------------------------------------
    // Package Methods
    // --------------------------------------------------------------------------

    @Override
    public String description() {
        return format("Pending: %s", description);
    }

    // --------------------------------------------------------------------------
    // Constructors
    // --------------------------------------------------------------------------

    public Pending(String description, Report report) {
        super(description, report);
    }
}
