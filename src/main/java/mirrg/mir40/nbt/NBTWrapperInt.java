package mirrg.mir40.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

public class NBTWrapperInt extends NBTWrapperBase<NBTTagInt, Integer>
{

	public NBTWrapperInt(String name)
	{
		super(name);
	}

	public NBTWrapperInt(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.INT;
	}

	@Override
	protected Integer readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getInteger(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, Integer value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setInteger(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
