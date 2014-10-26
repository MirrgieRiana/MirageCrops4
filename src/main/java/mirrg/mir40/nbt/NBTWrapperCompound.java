package mirrg.mir40.nbt;

import net.minecraft.nbt.NBTTagCompound;

public class NBTWrapperCompound extends NBTWrapperBase<NBTTagCompound, NBTTagCompound>
{

	public NBTWrapperCompound(String name)
	{
		super(name);
	}

	public NBTWrapperCompound(String name, NBTWrapperCompound parent)
	{
		super(name, parent);
	}

	@Override
	public final EnumNBTTypes getNbtType()
	{
		return EnumNBTTypes.COMPOUND;
	}

	@Override
	public NBTTagCompound find(NBTTagCompound nbt)
	{
		return (NBTTagCompound) super.find(nbt);
	}

	/**
	 * この要素が表すNBTタグが存在しない場合、存在している状態にする。<br>
	 * この要素がルートである場合、ぬるぽ例外を出す。<br>
	 * これを行うと{@link #isReadable(NBTTagCompound)}がtrueになる。
	 */
	public final void prepare(NBTTagCompound nbt)
	{
		if (!isWritable(nbt)) {
			getParent().prepare(nbt); // この時点で書き込み可能に
		}
		if (!isReadable(nbt)) {
			findParent(nbt).setTag(getName(), new NBTTagCompound());// この時点で読み込み可能に
		}
	}

	@Override
	protected NBTTagCompound readFromParent(NBTTagCompound parentNbt)
	{
		if (isReadableFromParent(parentNbt)) {
			return parentNbt.getCompoundTag(getName());
		} else {
			return null;
		}
	}

	@Override
	protected void writeToParent(NBTTagCompound parentNbt, NBTTagCompound value)
	{
		if (isWritableToParent(parentNbt)) {
			parentNbt.setTag(getName(), value);
		} else {
			throw new NullPointerException("" + parentNbt);
		}
	}

}
