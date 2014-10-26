package mirrg.mir40.nbt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;

public class NBTWrapperFloat extends NBTWrapperBase<NBTTagFloat, Float>
{

	public NBTWrapperFloat(String name)
	{
		super(name);
	}

	public NBTWrapperFloat(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.FLOAT;
	}

	@Override
	protected Float readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getFloat(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, Float value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setFloat(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
