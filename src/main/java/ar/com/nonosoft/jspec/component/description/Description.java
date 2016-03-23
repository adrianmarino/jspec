package ar.com.nonosoft.jspec.component.description;

import ar.com.nonosoft.jspec.block.context.ContextBlock;
import ar.com.nonosoft.jspec.component.Component;
import ar.com.nonosoft.jspec.component.context.impl.RootContext;


public abstract class Description<SUBJECT> extends Component<Description<SUBJECT>, SUBJECT> {

	public Description(String description, Component parent) {
		super(description, parent);
	}

	public Description(String description) {
		super(description);
	}

	public void context(String description, ContextBlock<SUBJECT> block) {
		new RootContext<>(description, block);
	}
}
