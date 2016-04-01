package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.ParentContextBlock;
import ar.com.nonosoft.jspec.output.report.Report;


public abstract class Description<SUBJECT> extends Container<Description<SUBJECT>,SUBJECT> {

	public Description(String description, Container parent, Report report) {
		super(description, parent, report);
	}

	public Description(String description, Report report) {
		super(description, report);
	}

	public void context(String description, ParentContextBlock<SUBJECT> block) {
		new ParentContext<>(description, block, this, report);
	}
}
