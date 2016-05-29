package ar.com.nonosoft.jspec.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassUtils {

	public static String nameOfSubclass(Class clazz) throws ClassNotFoundException {
		return genericClassOfSubclass(clazz, 0);
	}

	public static String genericClassOfSubclass(Class clazz, Integer number) throws ClassNotFoundException {
		return genericOfSubclass(clazz, number).getTypeName();
	}

	public static Type genericOfSubclass(Class clazz, Integer number) {
		ParameterizedType type = ((ParameterizedType) clazz.getGenericSuperclass());
		return type.getActualTypeArguments()[number];
	}

	private ClassUtils() {}
}
