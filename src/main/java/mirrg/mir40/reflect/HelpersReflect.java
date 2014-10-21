package mirrg.mir40.reflect;

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

}
