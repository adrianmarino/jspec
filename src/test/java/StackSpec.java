import java.util.Stack;

import static ar.com.nonosoft.jspec.JSpec.describe;

public class StackSpec {
	public static void main(String[] args) {
		describe(Stack.class, d -> {
			d.describe("#new", m -> {
				m.context("when create an empty stack", c -> {
					c.it("is empty", expect -> expect.that(new Stack()).isEmpty());
				});
			});

			d.describe(".push", m -> {
				m.context("when push new element onto top", c -> {
					c.it("has an element onto top", expect -> {
						Stack<Integer> stack = new Stack<>();
						stack.push(1);

						expect.that(stack.get(0)).isEqualTo(1);
					});
				});
			});

			d.describe(".pop", m -> {
				m.it("element is the first of stack when pop the last element", expect -> {
					Stack<Integer> stack = new Stack<>();
					stack.push(2);
					stack.push(1);

					expect.that(stack.pop()).isEqualTo(2);
				});
			});
		});
	}
}