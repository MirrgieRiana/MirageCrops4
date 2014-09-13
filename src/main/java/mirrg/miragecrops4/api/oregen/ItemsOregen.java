package mirrg.miragecrops4.api.oregen;

import mirrg.mir40.glob.api.IGlob;
import mirrg.mir40.glob.api.ISlot;
import net.minecraft.block.Block;

public class ItemsOregen
{

	/**
	 * 要素は16個まで
	 */
	public interface IEnumGlob
	{

		public void setGlob(IGlob glob);

		public IGlob getGlob();

	}

	public static enum EnumGlobsCalciteGroup implements IEnumGlob
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

	public static enum EnumGlobsMohsHardnessCrystal implements IEnumGlob
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

	public static enum EnumGlobsOtherMetal implements IEnumGlob
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

	public static enum EnumGlobsMirageMagic implements IEnumGlob
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

	public static ISlot slotOre;

	public static Block blockOreCalciteGroup;
	public static Block blockOreMohsHardnessCrystal;
	public static Block blockOreOtherMetal;
	public static Block blockOreMirageMagic;

}
