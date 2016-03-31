package ar.com.nonosoft.jspec.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassUtils {

	public static Type subclassGenericParamOf(Class clazz) {
		return subclassGenericParamOf(clazz, 0);
	}

	public static Type subclassGenericParamOf(Class clazz, Integer number) {
		Type specSubclass = clazz.getGenericSuperclass();
		return ((ParameterizedType) specSubclass).getActualTypeArguments()[number];
	}

	private ClassUtils() {}
}
