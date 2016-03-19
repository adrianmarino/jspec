package ar.com.nonosoft.jspec;

import java.util.Collection;

import static java.lang.String.format;

public class BoundExpectation<T> {

	private T value;

	public BoundExpectation(T value) {
		this.value = value;
	}

	public void isEqualTo(T expected) {
		assertThat(value.equals(expected), format("unexpected %s value. Should be %s.", value, expected));
	}

	public void isEmpty() {
	 assertThat(asCollection().isEmpty(), format("'%s' isn't empty!", asCollection()));
	}

	private Collection<?> asCollection() {
		return (Collection<?>) value;
	}

	private void assertThat(boolean expression, String failMessage) {
		if(!expression) throw new AssertionError(failMessage);
	}
}
