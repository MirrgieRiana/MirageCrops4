package mirrg.mir40.nbt;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;

public class NBTWrapperByte extends NBTWrapperBase<NBTTagByte, Byte>
{

	public NBTWrapperByte(String name)
	{
		super(name);
	}

	public NBTWrapperByte(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.BYTE;
	}

	@Override
	protected Byte readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getByte(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, Byte value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setByte(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
