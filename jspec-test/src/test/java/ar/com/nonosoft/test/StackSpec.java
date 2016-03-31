package ar.com.nonosoft.test;

import ar.com.nonosoft.jspec.Spec;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class StackSpec extends Spec<Stack> {{
	let("one", 1).let("two", 2);

	describe(".empty", () -> {
		context("when create an empty stack", () -> {
			subject(new Stack<Integer>());
			it("is empty", () -> expectThat(subject().isEmpty(), is(true)));
		});
	});

	describe(".push", () -> {
		context("when push new element onto top", () -> {
			subject(new Stack<Integer>() {{ push(spec.get("one")); }});
			it("has an element onto top", () -> expectThat(subject().get(0), is(equalTo(get("one")))));
		});
	});

	describe(".pop", () -> {
		context("when pop the last element", () -> {
			subject(new Stack<Integer>() {{
				push(spec.get("one"));
				push(spec.get("two"));
			}});
			it("element is the last pushed", () -> expectThat(subject().pop(), is(equalTo(get("two")))));
		});
	});
}}
