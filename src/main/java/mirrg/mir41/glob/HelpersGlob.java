package mirrg.mir41.glob;

import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.IGlobManager;
import mirrg.mir41.glob.api.ISlot;
import net.minecraft.item.ItemStack;

public class HelpersGlob
{

	public static <SLOT extends ISlot, GLOB extends IGlob>
		ItemStack get(IGlobManager<SLOT, GLOB> globManager, SLOT slot, GLOB glob)
	{
		return globManager.get(globManager.getDictionaryName(slot, glob));
	}

	public static <SLOT extends ISlot, GLOB extends IGlob>
		ItemStack copy(IGlobManager<SLOT, GLOB> globManager, SLOT slot, GLOB glob)
	{
		return get(globManager, slot, glob).copy();
	}

	public static <SLOT extends ISlot, GLOB extends IGlob>
		ItemStack copy(IGlobManager<SLOT, GLOB> globManager, SLOT slot, GLOB glob, int size)
	{
		ItemStack i = copy(globManager, slot, glob);
		i.stackSize = size;
		return i;
	}

}
