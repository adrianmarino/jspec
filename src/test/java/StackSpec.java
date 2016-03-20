import java.util.Stack;

import static ar.com.nonosoft.jspec.structs.JSpec.describe;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class StackSpec {
	public static void main(String[] args) {
		describe(Stack.class, d -> {
			d.describe("#new", () -> {
				d.context("when create an empty stack", c -> {
					c.subject(() -> new Stack());

					c.it("is empty", expect -> expect.that(c.subject().isEmpty(), is(true)));
				});
			});

			d.describe(".push", () -> {
				d.context("when push new element onto top", c -> {
					c.subject(()-> {
						Stack<Integer> stack = new Stack<>();
						stack.push(1);
						return stack;
					});

					c.it("has an element onto top", expect -> expect.that(c.subject().get(0), is(equalTo(1))));
				});
			});

			d.describe(".pop", () -> {
				d.subject(()-> {
					Stack<Integer> stack = new Stack<>();
					stack.push(2);
					stack.push(1);
					return stack;
				});

				d.it("element is the first of stack when pop the last element", expect -> {
					expect.that(d.subject().pop(), is(equalTo(2)));
				});
			});
		});
	}
}