package mirrg.mir40.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagShort;

public class NBTWrapperShort extends NBTWrapperBase<NBTTagShort, Short>
{

	public NBTWrapperShort(String name)
	{
		super(name);
	}

	public NBTWrapperShort(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.SHORT;
	}

	@Override
	protected Short readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getShort(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, Short value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setShort(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
