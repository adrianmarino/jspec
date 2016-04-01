package ar.com.nonosoft.jspec.assertion;

import org.hamcrest.Matcher;

import static org.junit.Assert.assertThat;

public class Expect {
	public <T> void that(T actual, Matcher<? super T> matcher) {
		assertThat(actual, matcher);
	}

	public <T> void that(String reason, T actual, Matcher<? super T> matcher) {
		assertThat(reason, actual, matcher);
	}
}