package ar.com.nonosoft.jspec.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassUtils {

	public static Class genericClassOfSubclass(Class clazz) throws ClassNotFoundException {
		return genericClassOfSubclass(clazz, 0);
	}

	public static Class genericClassOfSubclass(Class clazz, Integer number) throws ClassNotFoundException {
		return Class.forName(genericOfSubclass(clazz, number).getTypeName());
	}

	public static Type genericOfSubclass(Class clazz) {
		return genericOfSubclass(clazz, 0);
	}

	public static Type genericOfSubclass(Class clazz, Integer number) {
		ParameterizedType type = ((ParameterizedType) clazz.getGenericSuperclass());
		return type.getActualTypeArguments()[number];
	}

	private ClassUtils() {}
}
