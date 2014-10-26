package mirrg.mir40.nbt.example;

import net.minecraft.nbt.NBTTagCompound;

public class Example
{

	public static class EntityCard
	{

		public void tick(NBTTagCompound nbt)
		{
			if (!NBTWrapperExample.nbtWrapper.hull2.e.isReadable(nbt)) return;

			byte e = NBTWrapperExample.nbtWrapper.hull2.e.readFromNBT(nbt);
			e += 10;
			NBTWrapperExample.nbtWrapper.hull2.e.writeToNBT(nbt, e);
		}

	}

}
