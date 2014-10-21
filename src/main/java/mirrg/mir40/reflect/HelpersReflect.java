package mirrg.mir40.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class HelpersReflect
{

	public static Exception setStaticField(Class<?> clazz, String fieldName, Object object)
	{
		try {
			clazz.getField(fieldName).set(null, object);
			return null;
		} catch (IllegalArgumentException e) {
			return e;
		} catch (SecurityException e) {
			return e;
		} catch (IllegalAccessException e) {
			return e;
		} catch (NoSuchFieldException e) {
			return e;
		}
	}

	public static Object getStaticField(Class<?> clazz, String fieldName)
	{
		try {
			return clazz.getField(fieldName).get(null);
		} catch (IllegalArgumentException e) {
			return e;
		} catch (SecurityException e) {
			return e;
		} catch (IllegalAccessException e) {
			return e;
		} catch (NoSuchFieldException e) {
			return e;
		}
	}

	/**
	 * destのうち、{@link ShadowedField}がついているpublic staticフィールドがsrcに代入される。<br>
	 * srcのうち、{@link ShadowingField}がついているpublic staticフィールドをdesuに代入する。<br>
	 * 代入に失敗した場合、そのエントリの代入を行わずに次のエントリの代入を行う。<br>
	 * 失敗した代入の例外は戻り値に返される。
	 *
	 * @see #shadowed(Class, Class, ArrayList)
	 * @see #shadowing(Class, Class, ArrayList)
	 */
	public static ArrayList<Exception> castShadow(Class<?> src, Class<?> dest)
	{
		ArrayList<Exception> exceptions = new ArrayList<Exception>();
		shadowing(src, dest, exceptions);
		shadowed(src, dest, exceptions);
		return exceptions;
	}

	public static void shadowing(Class<?> src, Class<?> dest, ArrayList<Exception> exceptions)
	{
		Field[] fields = src.getDeclaredFields();
		for (Field srcField : fields) {
			if (Modifier.isPublic(srcField.getModifiers())) {
				if (Modifier.isStatic(srcField.getModifiers())) {
					ShadowingField annotation = srcField.getAnnotation(ShadowingField.class);
					if (annotation != null) {

						// 代入成立

						String destFieldName;
						if (annotation.value().equals("")) {
							destFieldName = srcField.getName();
						} else {
							destFieldName = annotation.value();
						}

						Field destField;

						try {
							destField = src.getField(destFieldName);
						} catch (SecurityException e) {
							exceptions.add(e);
							continue;
						} catch (NoSuchFieldException e) {
							exceptions.add(e);
							continue;
						}

						try {
							destField.set(null, srcField.get(null));
						} catch (IllegalArgumentException e) {
							exceptions.add(e);
							continue;
						} catch (IllegalAccessException e) {
							exceptions.add(e);
							continue;
						}

					}
				}
			}
		}
	}

	public static void shadowed(Class<?> src, Class<?> dest, ArrayList<Exception> exceptions)
	{
		Field[] fields = dest.getDeclaredFields();
		for (Field destField : fields) {
			if (Modifier.isPublic(destField.getModifiers())) {
				if (Modifier.isStatic(destField.getModifiers())) {
					ShadowedField annotation = destField.getAnnotation(ShadowedField.class);
					if (annotation != null) {

						// 代入成立

						String srcFieldName;
						if (annotation.value().equals("")) {
							srcFieldName = destField.getName();
						} else {
							srcFieldName = annotation.value();
						}

						Field srcField;

						try {
							srcField = src.getField(srcFieldName);
						} catch (SecurityException e) {
							exceptions.add(e);
							continue;
						} catch (NoSuchFieldException e) {
							exceptions.add(e);
							continue;
						}

						try {
							destField.set(null, srcField.get(null));
						} catch (IllegalArgumentException e) {
							exceptions.add(e);
							continue;
						} catch (IllegalAccessException e) {
							exceptions.add(e);
							continue;
						}

					}
				}
			}
		}
	}

}
