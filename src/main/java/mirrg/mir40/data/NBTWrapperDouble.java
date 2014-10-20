package mirrg.mir40.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;

public class NBTWrapperDouble extends NBTWrapperBase<NBTTagDouble, Double>
{

	public NBTWrapperDouble(String name)
	{
		super(name);
	}

	public NBTWrapperDouble(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.DOUBLE;
	}

	@Override
	protected Double readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getDouble(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, Double value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setDouble(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
