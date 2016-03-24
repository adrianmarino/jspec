package ar.com.nonosoft.jspec.component.description;

import ar.com.nonosoft.jspec.block.context.ContextBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.component.context.impl.RootContext;
import ar.com.nonosoft.jspec.output.Report;


public abstract class Description<SUBJECT> extends Component<Description<SUBJECT>, SUBJECT> {

	public Description(String description, Component parent, Report report) {
		super(description, parent, report);
	}

	public Description(String description, Report report) {
		super(description, report);
	}

	public void context(String description, ContextBlock<SUBJECT> block) {
		new RootContext<>(description, block, report);
	}
}
