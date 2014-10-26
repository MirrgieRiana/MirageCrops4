package mirrg.miragecrops4.oregen.global;

import mirrg.mir40.icon.IMultiIconShape;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobGroup;
import mirrg.mir41.glob.Slot;
import mirrg.miragecrops4.lib.MultiIcons;
import mirrg.miragecrops4.lib.MultiIcons.EnumMultiIconShape;

public class GlobsOregen
{

	public static enum EnumSlotType
	{
		BLOCK, ITEM,
	}

	public static enum EnumSlot
	{
		ore(EnumSlotType.BLOCK, null),
		block(EnumSlotType.BLOCK, null),
		ingot(EnumSlotType.ITEM, MultiIcons.EnumMultiIconShape.INGOT),
		gem(EnumSlotType.ITEM, MultiIcons.EnumMultiIconShape.GEM),
		dust(EnumSlotType.ITEM, MultiIcons.EnumMultiIconShape.DUST), ;

		public final Slot slot;
		public final EnumSlotType type;
		public final EnumMultiIconShape enumMultiIconShape;

		private EnumSlot(EnumSlotType type, EnumMultiIconShape enumMultiIconShape)
		{
			slot = new Slot(name());
			this.type = type;
			this.enumMultiIconShape = enumMultiIconShape;
		}

		public IMultiIconShape icon()
		{
			return enumMultiIconShape.shape;
		}

	}

	public static enum EnumGlob
	{
		calcite,
		magnesite,
		siderite,
		rhodochrosite,
		smithsonite,
		sphaerocobaltite,
		gaspeite,
		otavite,
		talc,
		gypsum,
		//calcite,
		fluorite,
		apatite,
		orthoclase,
		certusQuartz,
		topaz,
		ruby,
		diamond,
		bismuth,
		spinatite,
		spinachium, ;

		public final Glob glob;

		private EnumGlob()
		{
			glob = new Glob(name());
		}

	}

	public static enum EnumGlobGroup
	{
		CalciteGroup,
		MohsHardnessCrystal,
		OtherMetal,
		MirageMagic,
		MirageMaterial, ;

		public final GlobGroup<Glob> globGroup;

		private EnumGlobGroup()
		{
			globGroup = new GlobGroup<Glob>(name());
		}

	}

	private static void setAllowsSlot(GlobGroup<Glob> globGroup,
		boolean ore, boolean block, boolean ingot, boolean gem, boolean dust)
	{
		globGroup.setAllowsSlot(EnumSlot.ore.slot, ore);
		globGroup.setAllowsSlot(EnumSlot.block.slot, block);
		globGroup.setAllowsSlot(EnumSlot.ingot.slot, ingot);
		globGroup.setAllowsSlot(EnumSlot.gem.slot, gem);
		globGroup.setAllowsSlot(EnumSlot.dust.slot, dust);
	}

	static
	{
		{
			GlobGroup<Glob> g = EnumGlobGroup.CalciteGroup.globGroup;
			setAllowsSlot(g, true, true, false, true, true);

			g.registerGlob(EnumGlob.calcite.glob);
			g.registerGlob(EnumGlob.magnesite.glob);
			g.registerGlob(EnumGlob.siderite.glob);
			g.registerGlob(EnumGlob.rhodochrosite.glob);
			g.registerGlob(EnumGlob.smithsonite.glob);
			g.registerGlob(EnumGlob.sphaerocobaltite.glob);
			g.registerGlob(EnumGlob.gaspeite.glob);
			g.registerGlob(EnumGlob.otavite.glob);
		}

		{
			GlobGroup<Glob> g = EnumGlobGroup.MohsHardnessCrystal.globGroup;
			setAllowsSlot(g, true, true, false, true, true);

			g.registerGlob(EnumGlob.talc.glob);
			g.registerGlob(EnumGlob.gypsum.glob);
			g.registerGlob(EnumGlob.calcite.glob);
			g.registerGlob(EnumGlob.fluorite.glob);
			g.registerGlob(EnumGlob.apatite.glob);
			g.registerGlob(EnumGlob.orthoclase.glob);
			g.registerGlob(EnumGlob.certusQuartz.glob);
			g.registerGlob(EnumGlob.topaz.glob);
			g.registerGlob(EnumGlob.ruby.glob);
			g.registerGlob(EnumGlob.diamond.glob);
		}

		{
			GlobGroup<Glob> g = EnumGlobGroup.OtherMetal.globGroup;
			setAllowsSlot(g, true, true, true, false, true);

			g.registerGlob(EnumGlob.bismuth.glob);
		}

		{
			GlobGroup<Glob> g = EnumGlobGroup.MirageMagic.globGroup;
			setAllowsSlot(g, true, false, false, true, true);

			g.registerGlob(EnumGlob.spinatite.glob);
		}

		{
			GlobGroup<Glob> g = EnumGlobGroup.MirageMaterial.globGroup;
			setAllowsSlot(g, false, true, true, false, true);

			g.registerGlob(EnumGlob.spinachium.glob);
		}

	}

}
