package ar.com.nonosoft.test;

import ar.com.nonosoft.jspec.SpecSuite;

import java.util.Stack;

import static java.lang.System.out;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class TestSuite {

    public static void main(String[] args) {
        SpecSuite suite = new SpecSuite();
        suite.addSpec(StackSpec.class);
        suite.addSpecsIn(PACKAGE);
        addStackSpec(suite);
        out.println(suite.run());
    }

    private static SpecSuite addStackSpec(SpecSuite suite) {
        return suite.describe(Stack.class, d -> {
            d.let("one", 1).let("two", 2);

            d.context("when create an empty stack", c -> {
                c.subject(new Stack<Integer>());

                c.it("is empty", expect -> expect.that(c.subject().isEmpty(), is(true)));
            });

            d.context("when push new element onto top", c -> {
                c.subject(() -> new Stack<Integer>() {{ push(c.get("one")); }});

                c.it("has an element onto top", expect -> {
                    expect.that(c.subject().get(0), is(equalTo(c.get("one"))));
                });
            });

            d.context("when pop the last element", (c) -> {
                c.subject(() -> new Stack<Integer>() {{ push(c.get("one")); push(c.get("two")); }});

                c.it("element is the last pushed", expect -> {
                });

                c.it("is a pending It");

                c.xit("is a pending It", (expect) -> expect.that(true, is(false)));
            });
        });
    }

    public static final String PACKAGE = "com.nonosoft.test";
}
