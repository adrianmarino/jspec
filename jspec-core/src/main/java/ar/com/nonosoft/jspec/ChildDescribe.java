package ar.com.nonosoft.jspec;

import ar.com.nonosoft.jspec.block.VoidBlock;
import ar.com.nonosoft.jspec.output.Report;

public class ChildDescribe<SUBJECT> extends Describe<SUBJECT> {

	// --------------------------------------------------------------------------
	// Constructors
	// --------------------------------------------------------------------------

	ChildDescribe(String description, VoidBlock block, Container parent, Report report) {
		super(description, parent, report);
		perform(() -> block.eval());
	}
}
