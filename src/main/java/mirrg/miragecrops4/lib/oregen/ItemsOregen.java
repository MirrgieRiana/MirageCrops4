package mirrg.miragecrops4.lib.oregen;

import mirrg.mir34.modding.IModule;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobManager;
import mirrg.mir41.glob.Slot;
import mirrg.miragecrops4.lib.oregen.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.oregen.multi.ItemMultiIconOregen;

public class ItemsOregen
{

	public static IModule moduleOregen;

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

}
