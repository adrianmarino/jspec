package ar.com.nonosoft.jspec.output;

import org.fusesource.jansi.Ansi.Color;

import static ar.com.nonosoft.jspec.util.AssertionErrorUtils.errorLines;
import static ar.com.nonosoft.jspec.util.collection.CollectionUtils.of;
import static org.atteo.evo.inflector.English.plural;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class Report {

    public void intFailures() {
        failures++;
    }

    public void incErrors() {
        errors++;
    }

    public void incTests() {
        tests++;
    }

    public void incPendings() {
        pendings++;
    }

    public void syncWith(Report report) {
        output.syncWith(report.output);
        tests += report.tests;
        failures += report.failures;
        errors += report.errors;
        pendings +=  report.pendings;
    }

    public String toString() {
        return output.nl().addln(footer()).toString();
    }

    public void printSuccess(Long id, String desc) {
        output.var(id, new Output().capGreen(desc).nl());
    }

    public void printPending(Long id, String desc) {
        output.var(id, new Output().capBoldYellow("Pending:").ws().capYellow(desc).nl());
    }

    public void printFail(Long itId, String desc, Throwable cause) {
        Output fail = new Output().capRed(desc).ws().boldRed("FAIL!").ws();
        errorLines(cause).forEach(line -> fail.boldRed(line).ws());
        output.var(itId, fail.nl());
        intFailures();
    }

    public void printError(Long itId, String desc, Throwable exception) {
        output.var(itId, new Output().capRed(desc).red(".").ws()
                .boldRed("ERROR:").ws()
                .capRed(exception.getMessage()).red("!").nl());
        incErrors();
    }

    public void addItVar(Long id) {
        output.var(id);
    }

    public void printHeader(String desc, Color color) {
        output.addCap(desc, color).nl().beginLevel();
    }

    public void printBoldHeader(String desc, Color color) {
        output.capBold(desc, color).nl().beginLevel();
    }

    public void printFooter() {
        output.endLevel();
    }

    public void specsNotFound() {
        output.boldln("Specs not found!", YELLOW);
    }

    // --------------------------------------------------------------------------
    // Private Methods
    // --------------------------------------------------------------------------

    private String footer() {
        return of("test", tests, "failure", failures, "error", errors, "pending", pendings)
                .filter(c -> c.getKey().equals("test") ? true : c.getValue() > 0)
                .map(c -> new Output().boldBlue(c.getValue()).ws().
                        boldBlue(plural(c.getKey(), c.getValue().intValue())))
                .reduce((o1, o2) -> o1.boldBlue(",").ws().add(o2))
                .get().boldBlue(".").toString();
    }

    // --------------------------------------------------------------------------
    // Attributes
    // --------------------------------------------------------------------------

    private long failures, errors, tests, pendings;

    private Output output;

    // --------------------------------------------------------------------------
    // Constructors
    // --------------------------------------------------------------------------

    public Report() {
        failures = errors = tests = pendings = 0;
        output = new Output();
    }
}
