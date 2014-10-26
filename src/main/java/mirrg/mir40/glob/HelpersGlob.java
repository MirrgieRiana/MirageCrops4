package mirrg.mir40.glob;

import mirrg.mir40.math.HelpersString;
import net.minecraft.item.ItemStack;

public class HelpersGlob
{

	public static String getDictionaryName(ISlot slot, IGlob glob)
	{
		return slot.getName() + HelpersString.toUpperCaseHead(glob.getName());
	}

	public static ItemStack copy(ISlot slot, IGlob glob, int stackSize)
	{
		ItemStack itemStack = glob.copy(slot);
		itemStack.stackSize = stackSize;
		return itemStack;
	}

	public static ItemStack copy(ISlot slot, IGlob glob)
	{
		return copy(slot, glob, 1);
	}

}
