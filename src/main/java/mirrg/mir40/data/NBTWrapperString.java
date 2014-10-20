package mirrg.mir40.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

public class NBTWrapperString extends NBTWrapperBase<NBTTagString, String>
{

	public NBTWrapperString(String name)
	{
		super(name);
	}

	public NBTWrapperString(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.STRING;
	}

	@Override
	protected String readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getString(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, String value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setString(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
