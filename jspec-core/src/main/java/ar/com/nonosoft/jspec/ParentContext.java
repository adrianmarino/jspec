package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentContextBlock;
import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.output.report.Report;

public class ParentContext<SUBJECT> extends Context<SUBJECT> {
	public ParentContext(String description, ParentContextBlock<SUBJECT> block, Container parent, Report report) {
		super(description, parent, report);
		perform(() -> block.eval(this));
	}

	public void context(String desc, VoidBlock block) {
		new ChildContext<SUBJECT>(desc, block, this, report);
	}
}
