import java.util.Stack;

import static ar.com.nonosoft.jspec.JSpec.describe;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class Suite {
	public static void main(String[] args)
	{
		describe(Stack.class, d -> {
			d.context("when create an empty stack", c -> {
				c.subject(() -> new Stack());
				c.it("is empty", expect -> expect.that(c.subject().isEmpty(), is(true)));
			});

			d.context("when push new element onto top", c -> {
				c.subject(()-> new Stack() {{ push(1); }} );
				c.it("has an element onto top", expect -> expect.that(c.subject().get(0), is(equalTo(1))));
			});

			d.context("when pop the last element", (c) -> {
				c.subject(()-> new Stack(){{ push(2); push(1); }});
				c.it("element is the last pushed", expect -> {
					expect.that(c.subject().pop(), is(equalTo(1)));
				});
			});
		});
	}
}