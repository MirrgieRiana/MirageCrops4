package mirrg.miragecrops4.oregen.global;

import mirrg.mir40.math.HelpersString;
import mirrg.mir41.glob.IGlobGroup;
import mirrg.mir41.glob.ISlot;
import mirrg.miragecrops4.oregen.global.ItemsOregen.EnumSlot;

public class HelpersOregen
{

	public static String getBlockUnlocalizedName(ISlot slot, IGlobGroup<?> globGroup)
	{
		if (slot.equals(ItemsOregen.EnumSlot.block.slot)) {
			return "block" + globGroup.getName();
		} else {
			return "block" + HelpersString.toUpperCaseHead(slot.getName()) + globGroup.getName();
		}
	}

	public static String getItemUnlocalizedName(ISlot slot)
	{
		return "item" + HelpersString.toUpperCaseHead(slot.getName());
	}

}
