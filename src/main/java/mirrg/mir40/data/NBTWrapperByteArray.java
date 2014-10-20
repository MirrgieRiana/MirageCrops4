package mirrg.mir40.data;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;

public class NBTWrapperByteArray extends NBTWrapperBase<NBTTagByte, byte[]>
{

	public NBTWrapperByteArray(String name)
	{
		super(name);
	}

	public NBTWrapperByteArray(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.BYTE_ARRAY;
	}

	@Override
	protected byte[] readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getByteArray(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, byte[] value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setByteArray(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
