package mirrg.mir40.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagLong;

public class NBTWrapperLong extends NBTWrapperBase<NBTTagLong, Long>
{

	public NBTWrapperLong(String name)
	{
		super(name);
	}

	public NBTWrapperLong(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.LONG;
	}

	@Override
	protected Long readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getLong(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, Long value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setLong(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
