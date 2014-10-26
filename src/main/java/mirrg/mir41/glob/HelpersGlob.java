package mirrg.mir41.glob;

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
		ItemStack itemStack = get(globManager, slot, glob);
		if (itemStack == null) throw new NullPointerException(slot.getName() + " " + glob.getName());
		return itemStack.copy();
	}

	public static <SLOT extends ISlot, GLOB extends IGlob>
		ItemStack copy(IGlobManager<SLOT, GLOB> globManager, SLOT slot, GLOB glob, int size)
	{
		ItemStack i = copy(globManager, slot, glob);
		i.stackSize = size;
		return i;
	}

}
