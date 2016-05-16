package ar.com.nonosoft.test;

import ar.com.nonosoft.jspec.Spec;
import java.util.Stack;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class StackSpec extends Spec<Stack> {{
	describe(d -> {
		d.let("one", ()-> 1).let("two", ()-> 2);

		d.describe(".new", ()-> {
			d.context("when create an empty stack", c -> {
				c.subject(new Stack<Integer>());
				c.it("is empty", expect -> expect.that(c.subject().isEmpty(), is(true)));
			});
		});

		d.describe("#push", ()-> {
			d.context("when push new element onto top", c -> {
				c.subject(new Stack<Integer>() {{ push(c.get("one")); }});

				c.it("has an element onto top", expect -> {
					expect.that(c.subject().get(0), is(equalTo(c.get("one"))));
				});
			});
		});

		d.describe("#pop", ()-> {
			d.context("when pop the last element", (c) -> {
				c.subject(new Stack<Integer>() {{ push(c.get("one")); push(c.get("two")); }});

				c.it("element is the last pushed", expect -> {
					expect.that(c.subject().pop(), is(equalTo(c.get("two"))));
				});
			});
		});

		d.describe("when use an spring context", ()-> {
			d.context(SampleContext.class);
			d.it("has a hello string", expect -> expect.that(d.bean("testString2"), is(equalTo("Hello"))));
		});
	});
}}
