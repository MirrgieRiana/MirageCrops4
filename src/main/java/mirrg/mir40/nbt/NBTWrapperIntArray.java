package mirrg.mir40.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;

public class NBTWrapperIntArray extends NBTWrapperBase<NBTTagIntArray, int[]>
{

	public NBTWrapperIntArray(String name)
	{
		super(name);
	}

	public NBTWrapperIntArray(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.INT_ARRAY;
	}

	@Override
	protected int[] readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getIntArray(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, int[] value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setIntArray(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
