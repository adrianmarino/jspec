import java.util.Stack;

import static ar.com.nonosoft.jspec.JSpec.describe;

public class StackSpec {
	public static void main(String[] args) {
		describe("A Stack", d -> {
			d.describe("#new", m -> {
				m.context("when create an empty stack", c -> {
					c.it("is empty when created", expect -> expect.that(new Stack()).isEmpty());
				});
			});

			d.describe(".push", m -> {
				m.context("when push new element onto top", c -> {
					c.it("when push new elements onto the top of the stack", expect -> {
						Stack<Integer> stack = new Stack<>();
						stack.push(1);

						expect.that(stack.get(0)).isEqualTo(1);
					});
				});
			});

			d.describe(".pop", m -> {
				m.it("pop the last element pushed onto the stack when pop the last element", expect -> {
					Stack<Integer> stack = new Stack<>();
					stack.push(2);
					stack.push(1);

					expect.that(stack.pop()).isEqualTo(1);
				});
			});
		});
	}
}