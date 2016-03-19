package ar.com.nonosoft.jspec;

public class Expect {
	public BoundExpectation that(Object value) {
		return new BoundExpectation(value);
	}
}