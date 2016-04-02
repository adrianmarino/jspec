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
* **jspec-test**: A sample test module.

### Install

**Step 1:** Clone the project.
```bash
git clone https://github.com/adrianmarino/jspec.git
```

**Step 2:** Install jspec core & gradle plugin to local maven repository.
```bash
gradle clean install -PexcludeTest; gradle build
```

**Step 3:** Run sample _StackSpec_ to check that all it working.
```bash
gradle -q jspec
```
You should view the next output:

![rspec output](https://raw.githubusercontent.com/adrianmarino/jspec/master/jspec-test/console.png)


### Use

## From gradle

**Step 1:** Add plugin to build.gradle.
```groovy
apply plugin: 'jspec'

buildscript {
	repositories {
		mavenLocal()
		mavenCentral()
	}
	dependencies {
		classpath 'ar.com.nonosoft:jspec-plugin:0.0.1'
	}
}
```

**Step 2:** Describe a spec.

```java
public class StackSpec extends Spec<Stack> {{
	describe(d -> {
		d.let("one", () -> 1).let("two", () -> 2);

		d.describe(".new", ()-> {
			d.context("when create an empty stack", c -> {
				c.subject(() -> new Stack<Integer>());
				c.it("is empty", expect -> expect.that(c.subject().isEmpty(), is(true)));
			});
		});

		d.describe("#push", ()-> {
			d.context("when push new element onto top", c -> {
				c.subject(() -> new Stack<Integer>() {{ push(c.get("one")); }});

				c.it("has an element onto top", expect -> {
					expect.that(c.subject().get(0), is(equalTo(c.get("one"))));
				});
			});
		});

		d.describe("#pop", ()-> {
			d.context("when pop the last element", (c) -> {
				c.subject(() -> new Stack<Integer>() {{ push(c.get("one")); push(c.get("two")); }});

				c.it("element is the last pushed", expect -> {
					expect.that(c.subject().pop(), is(equalTo(c.get("two"))));
				});
			});
		});
	});
}}
```

**Step 3:** Run all specs.
```bash
gradle -q jspec
```

Also could run specs in a particular package.
```bash
gradle -q jspec -Ppkg=ar.com.nonosoft.test
```


## Speficication Guide

 _Coming soon!_ (<a href="http://betterspecs.org">Better Specs documentation</a>).


### TODO

* Many tests.
* Spring context integration.
* Spec maven plugin.
* xit.
* let!
