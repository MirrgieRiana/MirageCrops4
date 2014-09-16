package mirrg.miragecrops4.api.oregen;

import java.util.LinkedList;

import mirrg.mir40.glob.api.IGlob;
import mirrg.mir40.glob.api.ISlot;
import net.minecraft.block.Block;

public class ItemsOregen
{

	/**
	 * 要素は16個まで
	 */
	public interface IEnumGlobs
	{

		public void setGlob(IGlob glob);

		public IGlob getGlob();

	}

	public static enum EnumGlobsCalciteGroup implements IEnumGlobs
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

		@Override
		public IGlob getGlob()
		{
			return glob;
		}

		@Override
		public void setGlob(IGlob glob)
		{
			this.glob = glob;
		}

	}

	public static enum EnumGlobsMohsHardnessCrystal implements IEnumGlobs
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

		@Override
		public IGlob getGlob()
		{
			return glob;
		}

		@Override
		public void setGlob(IGlob glob)
		{
			this.glob = glob;
		}

	}

	public static enum EnumGlobsOtherMetal implements IEnumGlobs
	{
		bismuth, ;

		public IGlob glob;

		@Override
		public IGlob getGlob()
		{
			return glob;
		}

		@Override
		public void setGlob(IGlob glob)
		{
			this.glob = glob;
		}

	}

	public static enum EnumGlobsMirageMagic implements IEnumGlobs
	{
		spinatite, ;

		public IGlob glob;

		@Override
		public IGlob getGlob()
		{
			return glob;
		}

		@Override
		public void setGlob(IGlob glob)
		{
			this.glob = glob;
		}

	}

	public static LinkedList<IEnumGlobs[]> enumGlobsList = new LinkedList<IEnumGlobs[]>();
	{
		enumGlobsList.add(EnumGlobsCalciteGroup.values());
		enumGlobsList.add(EnumGlobsMohsHardnessCrystal.values());
		enumGlobsList.add(EnumGlobsOtherMetal.values());
		enumGlobsList.add(EnumGlobsMirageMagic.values());
	}

	public static ISlot slotOre;

	public static Block blockOreCalciteGroup;
	public static Block blockOreMohsHardnessCrystal;
	public static Block blockOreOtherMetal;
	public static Block blockOreMirageMagic;

}
