package mirrg.miragecrops4.api.oregen;

import mirrg.mir40.glob.api.IGlob;
import mirrg.mir40.glob.api.ISlot;
import net.minecraft.block.Block;

public class ItemsOregen
{

	public static enum EnumGlobsCalciteGroup
	{
		calcite,
		magnesite,
		siderite,
		rhodochrosite,
		smithsonite,
		sphaerocobaltite,
		gaspeite,
		otavite, ;

		public IGlob glob;
	}

	public static enum EnumGlobsMohsHardnessCrystal
	{
		talc,
		gypsum,
		calcite,
		fluorite,
		apatite,
		orthoclase,
		certusQuartz,
		topaz,
		ruby,
		diamond, ;

		public IGlob glob;
	}

	public static enum EnumGlobsOtherMetal
	{
		bismuth, ;

		public IGlob glob;
	}

	public static enum EnumGlobsMirageMagic
	{
		spinatite, ;

		public IGlob glob;
	}

	public static ISlot slotOre;

	public static Block blockOreCalciteGroup;
	public static Block blockOreMohsHardnessCrystal;
	public static Block blockOreOtherMetal;
	public static Block blockOreMirageMagic;

}
