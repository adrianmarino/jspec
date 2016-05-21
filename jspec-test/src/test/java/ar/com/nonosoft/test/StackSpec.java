package ar.com.nonosoft.test;

import ar.com.nonosoft.jspec.Spec;
import java.util.Stack;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class StackSpec extends Spec<Stack> {{
	describe(d -> {
		d.let("one", 1).let("two", 2);

		d.describe(".new", ()-> {
			d.context("when create an empty stack", c -> {
                c.subject(() -> new Stack<Integer>() {{ push(c.get("one")); }});
				c.it("is empty", expect -> expect.that(c.subject().isEmpty(), is(true)));
			});
		});

		d.describe("#push", ()-> {
			d.context("when push new element onto top", c -> {
				c.subject(() -> new Stack<Integer>() {{ push(c.get("one")); }});

				c.it("has an element onto top", expect -> expect.that(c.subject().get(0), is(equalTo(c.get("one")))));
			});
		});

		d.describe("#pop", ()-> {
			d.context("when pop the last element", (c) -> {
				c.subject(() -> new Stack<Integer>() {{ push(c.get("one")); push(c.get("two")); }});

				c.it("element is the last pushed", expect -> expect.that(c.subject().pop(), is(equalTo(c.get("two")))));
			});
		});

		d.context("when use an spring context", (c)-> {
			c.context(SampleContext.class);
			c.it("has a hello string", expect -> {
                expect.that(c.bean("testString"), is(equalTo("Hello")));
            });
		});

		d.it("is a pending it");

		d.xit("is a pending xit", (expect) -> expect.that(true, is(false)));
	});
}}
