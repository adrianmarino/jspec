package ar.com.nonosoft.jspec;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.*;

public class LetSpec extends Spec<List<Integer>> {{
    describe((d)-> {
        d.context("when has a subject with a numbers list", (c) -> {
            c.subject(()-> newArrayList(1, 2, 3));

            c.it("has numbers", (expect -> {
                c.subject().add(4);
                expect.that(c.subject(), contains(1, 2, 3, 4));
            }));

            c.it("hasn't numbers added on a previous test", (expect) -> {
                expect.that(c.subject(), not(hasItems(4)));
            });
        });

        d.context("when has a let with a numbers list", (c) -> {
            c.let("numbers", ()-> newArrayList(1, 2, 3));

            c.it("has numbers", (expect -> {
                List<Integer> numbers = c.get("numbers");
                numbers.add(4);
                expect.that(numbers, contains(1, 2, 3, 4));
            }));

            c.it("hasn't numbers added on a previous test", (expect) -> {
                expect.that(c.get("numbers"), not(hasItems(4)));
            });
        });
    });
}}
