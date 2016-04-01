package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.output.report.Report;

public class ChildContext<SUBJECT> extends Context<SUBJECT> {
	public ChildContext(String description, VoidBlock block, Container parent, Report report) {
		super(description, parent, report);
		perform(() -> block.eval());
	}
}
