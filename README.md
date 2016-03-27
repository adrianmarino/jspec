[![License](http://img.shields.io/:license-mit-blue.svg)](http://badges.mit-license.org)


# JSpec

An RSpec like micro framework for java language.

### Features

* Describe your tests as RSpec way.
* Run a JSpec specification as a JUnit test from your favorite IDE (Idea/Eclipse/Netbeans).
* Run tests with gradle as "rspec --format=doc" way.

### Modules

* **jspec-core**: The jspec framework.
* **jspec-plugin**: The spec gradle plugin. This allow run tests from console.


### Install

Install jspec-plugin/code in local maven repository

```bash
gradle clean install -PexcludeTest
```

### Test

```bash
gradle -q spec
```

### Use

1. Describe a specification

```java
public class StackSpec extends Specification<Stack> {{
	describe(Stack.class, d -> {
		d.let("one", 1).let("two", 2);

		d.context("when create an empty stack", c -> {
			c.subject(new Stack<Integer>());

			c.it("is empty", expect -> expect.that(c.subject().isEmpty(), is(true)));
		});

		d.context("when push new element onto top", c -> {
			c.subject(new Stack<Integer>() {{ push(c.get("one")); }});

			c.it("has an element onto top", expect -> {
				expect.that(c.subject().get(0), is(equalTo(c.get("one"))))
			});
		});

		d.context("when pop the last element", c -> {
			c.subject(new Stack<Integer>() {{ push(c.get("one")); push(c.get("two")); }});

			c.it("element is the last pushed", expect -> {
				expect.that(c.subject().pop(), is(equalTo(c.get("two"))))
			});
		});
	});
}}
```

2. run tests

![rspec output](https://raw.githubusercontent.com/adrianmarino/jspec/master/jspec-test/console.png)
