package ar.com.nonosoft.test.jspec.component.description;

import ar.com.nonosoft.test.jspec.block.context.ContextBlock;
import ar.com.nonosoft.test.jspec.component.Component;
import ar.com.nonosoft.test.jspec.component.context.impl.RootContext;
import ar.com.nonosoft.test.jspec.output.report.Report;


public abstract class Description<SUBJECT> extends Component<Description<SUBJECT>,SUBJECT> {

	public Description(String description, Component parent, Report report) {
		super(description, parent, report);
	}

	public Description(String description, Report report) {
		super(description, report);
	}

	public void context(String description, ContextBlock<SUBJECT> block) {
		new RootContext<>(description, block, this, report);
	}
}
