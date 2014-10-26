package mirrg.miragecrops4.lib.fairycrop;

import net.minecraft.nbt.NBTTagCompound;

public interface IFairyTag
{

	public void readFromNBT(NBTTagCompound nbt);

	public void writeToNBT(NBTTagCompound nbt);

}
