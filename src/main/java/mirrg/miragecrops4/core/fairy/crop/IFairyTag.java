package mirrg.miragecrops4.core.fairy.crop;

import net.minecraft.nbt.NBTTagCompound;

public interface IFairyTag
{

	public void readFromNBT(NBTTagCompound nbt);

	public void writeToNBT(NBTTagCompound nbt);

}