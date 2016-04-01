package ar.com.nonosoft.jspec.container.context;

import ar.com.nonosoft.jspec.container.Container;
import ar.com.nonosoft.jspec.output.report.Report;

import static ar.com.nonosoft.jspec.util.StringUtils.withFgColor;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;

public abstract class Context<SUBJECT> extends Container<Context<SUBJECT>,SUBJECT> {

	public Context(String description, Container parent, Report report) {
		super(description, parent, report);
	}

	public Context(String description, Report report) {
		super(description, report);
	}

	protected void printHeader() {
		report.output().println(withFgColor(capitalize(description()), DEFAULT)).beginLevel();
	}

	protected void printFooter() {
		report.output().endLevel();
	}
}
