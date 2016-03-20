package ar.com.nonosoft.jspec;

public class JSpec {

	public static void describe(Class clazz, DescriptionBlock block) {
		describe(clazz.getSimpleName(), block);
	}

	public static void describe(String desc, DescriptionBlock block) {
		new Description(desc, block);
	}

	private JSpec() {}
}
