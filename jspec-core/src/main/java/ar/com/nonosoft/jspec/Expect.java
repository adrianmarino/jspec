package ar.com.nonosoft.jspec;

import org.hamcrest.Matcher;

import static org.junit.Assert.assertThat;

/**
 * User to check an expected condition in {@link ar.com.nonosoft.jspec.block.ItBlock} blocks.
 */
public class Expect {

	/**
	 * Check expected condition using Hamcrest matchers.
	 *
	 * @see <a href="https://code.google.com/archive/p/hamcrest/wikis/Tutorial.wiki">The Hamcrest Tutorial</a>
	 */
	public <T> void that(T actual, Matcher<? super T> matcher) {
		assertThat(actual, matcher);
	}

	/**
	 * Check expected condition using Hamcrest matchers.
	 *
	 * @see <a href="https://code.google.com/archive/p/hamcrest/wikis/Tutorial.wiki">The Hamcrest Tutorial</a>
	 */
	public <T> void that(String reason, T actual, Matcher<? super T> matcher) {
		assertThat(reason, actual, matcher);
	}
}