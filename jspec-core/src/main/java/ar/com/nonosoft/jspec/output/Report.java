package ar.com.nonosoft.jspec.output;

import org.fusesource.jansi.Ansi.Color;

import static ar.com.nonosoft.jspec.util.AssertionErrorUtils.errorLines;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class Report {

    public void intFailures() {
        failures++;
    }

    public void incErrors() {
        errors++;
    }

    public void incTestCounter() {
        tests++;
    }

    public void syncWith(Report report) {
        output.syncWith(report.output);
        tests += report.tests;
        failures += report.failures;
        errors += report.errors;
    }

    public String toString() {
        return output.nl().addln(footer()).toString();
    }

    public void printSuccess(Long itId, String desc) {
        output.var(itId, new Output().capGreen(desc).nl());
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

    // --------------------------------------------------------------------------
    // Private Methods
    // --------------------------------------------------------------------------

    private String footer() {
        return new Output().boldBlue(tests).ws().boldBlue(tests == 1 ? "test" : "tests").boldBlue(",").ws()
                .boldBlue(failures).ws().boldBlue(failures == 1 ? "failure" : "failures").boldBlue(",").ws()
                .boldBlue(errors).ws().boldBlue(errors == 1 ? "incErrors" : "errors").boldBlue(".").toString();
    }

    public void specsNotFound() {
        output.boldln("Specs not found!", YELLOW);
    }

    // --------------------------------------------------------------------------
    // Attributes
    // --------------------------------------------------------------------------

    private long failures, errors, tests;

    private Output output;

    // --------------------------------------------------------------------------
    // Constructors
    // --------------------------------------------------------------------------

    public Report() {
        failures = errors = tests = 0;
        output = new Output();
    }
}
