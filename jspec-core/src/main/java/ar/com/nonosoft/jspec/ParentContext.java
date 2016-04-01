package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentContextBlock;
import ar.com.nonosoft.jspec.output.report.Report;

public class ParentContext<SUBJECT> extends Context<SUBJECT> {

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	ParentContext(String description, ParentContextBlock<SUBJECT> block, Container parent, Report report) {
		super(description, parent, report);
		perform(() -> block.eval(this));
	}
}
