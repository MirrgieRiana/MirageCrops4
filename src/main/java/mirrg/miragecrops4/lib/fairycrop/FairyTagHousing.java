package mirrg.miragecrops4.lib.fairycrop;

import net.minecraft.nbt.NBTTagCompound;

public class FairyTagHousing implements IFairyTag
{
	public int population;
	public int populationMax;

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		population = nbt.getInteger("population");
		populationMax = nbt.getInteger("populationMax");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("population", population);
		nbt.setInteger("populationMax", populationMax);
	}

}
