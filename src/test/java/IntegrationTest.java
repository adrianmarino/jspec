import ar.com.nonosoft.jspec.SpecSuite;
import ar.com.nonosoft.jspec.SpecWriter;
import com.nonosoft.test.StackSpec;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class IntegrationTest {
	public static void main(String[] args) {
		new SpecSuite()
				.addSpec(StackSpec.class)
				.addAllSpecsOn("com.nonosoft.test")
				.addWriter(stackSpec())
				.run();
	}

	private static SpecWriter stackSpec() {
		return new SpecWriter().describe(Stack.class, d -> {
			d.let("one", 1).let("two", 2);

			d.context("when create an empty stack", c -> {
				c.subject(new Stack());

				c.it("is empty", expect -> expect.that(c.subject().isEmpty(), is(true)));
			});

			d.context("when push new element onto top", c -> {
				c.subject(new Stack() {{ push(c.get("one")); }});

				c.it("has an element onto top", expect -> expect.that(c.subject().get(0), is(equalTo(c.get("one")))));
			});

			d.context("when pop the last element", (c) -> {
				c.subject(new Stack() {{ push(c.get("one")); push(c.get("two")); }});

				c.it("element is the last pushed", expect -> {expect.that(c.subject().pop(), is(equalTo(c.get("two")))); });
			});
		});
	}
}