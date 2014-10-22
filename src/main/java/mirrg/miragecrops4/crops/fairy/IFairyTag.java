package mirrg.miragecrops4.crops.fairy;

import net.minecraft.nbt.NBTTagCompound;

public interface IFairyTag
{

	public void readFromNBT(NBTTagCompound nbt);

	public void writeToNBT(NBTTagCompound nbt);

}
