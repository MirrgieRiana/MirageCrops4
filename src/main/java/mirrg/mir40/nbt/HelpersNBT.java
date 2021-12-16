package mirrg.mir40.nbt;

import java.util.Hashtable;

import mirrg.h.struct.Struct1;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class HelpersNBT
{

	public static final Class<NBTTagCompound> COMPOUND_TAG = NBTTagCompound.class;
	public static final Class<NBTBase> TAG = NBTBase.class;

	public static final Class<String> STRING = String.class;
	public static final Class<Boolean> BOOLEAN = Boolean.class;
	public static final Class<byte[]> BYTE_ARRAY = byte[].class;
	public static final Class<int[]> INT_ARRAY = int[].class;

	public static final Class<Byte> BYTE = Byte.class;
	public static final Class<Short> SHORT = Short.class;
	public static final Class<Integer> INTEGER = Integer.class;
	public static final Class<Long> LONG = Long.class;

	public static final Class<Float> FLOAT = Float.class;
	public static final Class<Double> DOUBLE = Double.class;

	public static final Hashtable<Class<?>, Integer> classOrdinalTable = new Hashtable<>();

	static
	{
		classOrdinalTable.put(BYTE, 1);
		classOrdinalTable.put(SHORT, 2);
		classOrdinalTable.put(INTEGER, 3);
		classOrdinalTable.put(LONG, 4);
		classOrdinalTable.put(FLOAT, 5);
		classOrdinalTable.put(DOUBLE, 6);
		classOrdinalTable.put(BYTE_ARRAY, 7);
		classOrdinalTable.put(STRING, 8);
		classOrdinalTable.put(COMPOUND_TAG, 10);
		classOrdinalTable.put(INT_ARRAY, 11);
	}

	@SuppressWarnings("unchecked")
	public static <T> T wrapGet(NBTTagCompound nbt, String key, Class<T> clazz)
	{
		if (clazz == COMPOUND_TAG) return (T) nbt.getCompoundTag(key);
		if (clazz == TAG) return (T) nbt.getTag(key);

		if (clazz == STRING) return (T) nbt.getString(key);
		if (clazz == BOOLEAN) return (T) Boolean.valueOf(nbt.getBoolean(key));
		if (clazz == BYTE_ARRAY) return (T) nbt.getByteArray(key);
		if (clazz == INT_ARRAY) return (T) nbt.getIntArray(key);

		if (clazz == BYTE) return (T) Byte.valueOf(nbt.getByte(key));
		if (clazz == SHORT) return (T) Short.valueOf(nbt.getShort(key));
		if (clazz == INTEGER) return (T) Integer.valueOf(nbt.getInteger(key));
		if (clazz == LONG) return (T) Long.valueOf(nbt.getLong(key));

		if (clazz == FLOAT) return (T) Float.valueOf(nbt.getFloat(key));
		if (clazz == DOUBLE) return (T) Double.valueOf(nbt.getDouble(key));

		return null;
	}

	public static <T> void wrapSet(NBTTagCompound nbt, String key, T value, Class<T> clazz)
	{
		if (clazz == COMPOUND_TAG) nbt.setTag(key, (NBTTagCompound) value);
		else if (clazz == TAG) nbt.setTag(key, (NBTBase) value);

		else if (clazz == STRING) nbt.setString(key, (String) value);
		else if (clazz == BOOLEAN) nbt.setBoolean(key, (Boolean) value);
		else if (clazz == BYTE_ARRAY) nbt.setByteArray(key, (byte[]) value);
		else if (clazz == INT_ARRAY) nbt.setIntArray(key, (int[]) value);

		else if (clazz == BYTE) nbt.setByte(key, (Byte) value);
		else if (clazz == SHORT) nbt.setShort(key, (Short) value);
		else if (clazz == INTEGER) nbt.setInteger(key, (Integer) value);
		else if (clazz == LONG) nbt.setLong(key, (Long) value);

		else if (clazz == FLOAT) nbt.setFloat(key, (Float) value);
		else if (clazz == DOUBLE) nbt.setDouble(key, (Double) value);
		else throw new IllegalArgumentException(clazz.getName());
	}

	// 

	/**
	 * pathが1つ以上の/を含む場合、/より左側をディレクトリとして解釈し、再起的に参照したCompoundTagを返す。<br>
	 * その際、pathは/を含まないキー名がセットされる。<br>
	 * pathが/を含まない場合、何もせずにpathを返す。<br>
	 * 参照の途中でCompoundTagが指定されたディレクトリ名にCompoundTagを持たない場合、nullを返す。<br>
	 * その際、pathは見つからなかったディレクトリ名を含むパス名となる。
	 */
	public static NBTTagCompound offset(NBTTagCompound nbt, Struct1<String> path)
	{
		int index = path.x.indexOf('/');
		if (index == -1) return nbt;

		String dir = path.x.substring(0, index);
		if (!nbt.hasKey(dir, EnumNBTTypes.COMPOUND.ordinal())) return null;
		path.x = path.x.substring(index + 1);
		return offset(nbt.getCompoundTag(dir), path);
	}

	/**
	 * @param path
	 *            {@link #offset(NBTTagCompound, Struct1)}形式の/を含むパス
	 */
	public static <T> boolean hasKey(NBTTagCompound nbt, String path, int type)
	{
		Struct1<String> tmp = new Struct1<String>(path);
		nbt = offset(nbt, tmp);
		path = tmp.x;
		if (nbt == null) return false;

		return nbt.hasKey(path, type);
	}

	/**
	 * 取得可能でない場合、nullを返す。
	 * 
	 * @param path
	 *            {@link #offset(NBTTagCompound, Struct1)}形式の/を含むパス
	 */
	public static <T> T get(NBTTagCompound nbt, String path, Class<T> clazz)
	{
		Struct1<String> tmp = new Struct1<String>(path);
		nbt = offset(nbt, tmp);
		path = tmp.x;
		if (nbt == null) return null;

		if (!nbt.hasKey(path, classOrdinalTable.get(clazz))) return null;
		return wrapGet(nbt, path, clazz);
	}

	/**
	 * 代入が正常に行われた場合、trueを返す。
	 * 
	 * @param path
	 *            {@link #offset(NBTTagCompound, Struct1)}形式の/を含むパス
	 */
	public static <T> boolean set(NBTTagCompound nbt, String path, T value, Class<T> clazz)
	{
		Struct1<String> tmp = new Struct1<String>(path);
		nbt = offset(nbt, tmp);
		path = tmp.x;
		if (nbt == null) return false;

		wrapSet(nbt, path, value, clazz);
		return true;
	}

}
