package mirrg.miragecrops4.oregen.global;

import mirrg.mir40.math.HelpersString;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobManager;
import mirrg.mir41.glob.IGlobGroup;
import mirrg.mir41.glob.ISlot;
import mirrg.mir41.glob.Slot;
import mirrg.miragecrops4.lib.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.multi.ItemMultiIconOregen;
import mirrg.miragecrops4.oregen.ModuleOregen;

public class ItemsOregen
{

	public static ModuleOregen moduleOregen;

	public static GlobManager<Slot, Glob> globManager = new GlobManager<Slot, Glob>();

	public static BlockMultiOregen blockOreCalciteGroup;
	public static BlockMultiOregen blockOreMohsHardnessCrystal;
	public static BlockMultiOregen blockOreOtherMetal;
	public static BlockMultiOregen blockOreMirageMagic;

	public static BlockMultiOregen blockCalciteGroup;
	public static BlockMultiOregen blockMohsHardnessCrystal;
	public static BlockMultiOregen blockOtherMetal;
	public static BlockMultiOregen blockMirageMaterial;

	public static ItemMultiIconOregen itemIngot;
	public static ItemMultiIconOregen itemGem;
	public static ItemMultiIconOregen itemDust;

	public static String getBlockUnlocalizedName(ISlot slot, IGlobGroup<?> globGroup)
	{
		if (slot.equals(GlobsOregen.EnumSlot.block.slot)) {
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
