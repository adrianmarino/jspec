[![License](http://img.shields.io/:license-mit-blue.svg)](http://badges.mit-license.org)


# JSpec

An RSpec like micro framework for java language.

### Features

* Describe your tests as RSpec way.
* Run a JSpec specification as a JUnit test from your favorite IDE (Idea/Eclipse/Netbeans).
* Run tests with gradle as "rspec --format=doc" way.
* Use hamcrest assetions.
* **Still under construction!**

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

Step 1: Describe a specification

```java
public class StackSpec extends Spec<Stack> {{
	describe(d -> {
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
				expect.that(c.subject().pop(), is(equalTo(c.get("two"))));
			});
		});
	});
}}
```

Step 2: Run tests

![rspec output](https://raw.githubusercontent.com/adrianmarino/jspec/master/jspec-test/console.png)


### TODO

* Many tests.
* Spring context integration.
* Spec maven plugin.
* xit.
* let!
