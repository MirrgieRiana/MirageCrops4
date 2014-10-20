package mirrg.mir40.data;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;

public class NBTWrapperBoolean extends NBTWrapperBase<NBTTagByte, Boolean>
{

	public NBTWrapperBoolean(String name)
	{
		super(name);
	}

	public NBTWrapperBoolean(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.BYTE;
	}

	@Override
	protected Boolean readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getBoolean(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, Boolean value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setBoolean(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
