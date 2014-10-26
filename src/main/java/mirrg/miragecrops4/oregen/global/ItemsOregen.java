package mirrg.miragecrops4.oregen.global;

import mirrg.mir40.icon.IMultiIconShape;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobGroup;
import mirrg.mir41.glob.GlobManager;
import mirrg.mir41.glob.Slot;
import mirrg.miragecrops4.lib.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.multi.ItemMultiIconOregen;
import mirrg.miragecrops4.oregen.ModuleOregen;
import mirrg.miragecrops4.oregen.global.MultiIcons.EnumMultiIconShape;

public class ItemsOregen
{

	public static ModuleOregen moduleOregen;

	public static GlobManager<Slot, Glob> globManager = new GlobManager<Slot, Glob>();

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

	static
	{
		{
			GlobGroup<Glob> globGroup = EnumGlobGroup.CalciteGroup.globGroup;
			globGroup.setAllowsSlot(EnumSlot.ore.slot, true);
			globGroup.setAllowsSlot(EnumSlot.block.slot, true);
			globGroup.setAllowsSlot(EnumSlot.ingot.slot, false);
			globGroup.setAllowsSlot(EnumSlot.gem.slot, true);
			globGroup.setAllowsSlot(EnumSlot.dust.slot, true);
			globGroup.registerGlob(EnumGlob.calcite.glob);
			globGroup.registerGlob(EnumGlob.magnesite.glob);
			globGroup.registerGlob(EnumGlob.siderite.glob);
			globGroup.registerGlob(EnumGlob.rhodochrosite.glob);
			globGroup.registerGlob(EnumGlob.smithsonite.glob);
			globGroup.registerGlob(EnumGlob.sphaerocobaltite.glob);
			globGroup.registerGlob(EnumGlob.gaspeite.glob);
			globGroup.registerGlob(EnumGlob.otavite.glob);
		}

		{
			GlobGroup<Glob> globGroup = EnumGlobGroup.MohsHardnessCrystal.globGroup;
			globGroup.setAllowsSlot(EnumSlot.ore.slot, true);
			globGroup.setAllowsSlot(EnumSlot.block.slot, true);
			globGroup.setAllowsSlot(EnumSlot.ingot.slot, false);
			globGroup.setAllowsSlot(EnumSlot.gem.slot, true);
			globGroup.setAllowsSlot(EnumSlot.dust.slot, true);
			globGroup.registerGlob(EnumGlob.talc.glob);
			globGroup.registerGlob(EnumGlob.gypsum.glob);
			globGroup.registerGlob(EnumGlob.calcite.glob);
			globGroup.registerGlob(EnumGlob.fluorite.glob);
			globGroup.registerGlob(EnumGlob.apatite.glob);
			globGroup.registerGlob(EnumGlob.orthoclase.glob);
			globGroup.registerGlob(EnumGlob.certusQuartz.glob);
			globGroup.registerGlob(EnumGlob.topaz.glob);
			globGroup.registerGlob(EnumGlob.ruby.glob);
			globGroup.registerGlob(EnumGlob.diamond.glob);
		}

		{
			GlobGroup<Glob> globGroup = EnumGlobGroup.OtherMetal.globGroup;
			globGroup.setAllowsSlot(EnumSlot.ore.slot, true);
			globGroup.setAllowsSlot(EnumSlot.block.slot, true);
			globGroup.setAllowsSlot(EnumSlot.ingot.slot, true);
			globGroup.setAllowsSlot(EnumSlot.gem.slot, false);
			globGroup.setAllowsSlot(EnumSlot.dust.slot, true);
			globGroup.registerGlob(EnumGlob.bismuth.glob);
		}

		{
			GlobGroup<Glob> globGroup = EnumGlobGroup.MirageMagic.globGroup;
			globGroup.setAllowsSlot(EnumSlot.ore.slot, true);
			globGroup.setAllowsSlot(EnumSlot.block.slot, false);
			globGroup.setAllowsSlot(EnumSlot.ingot.slot, false);
			globGroup.setAllowsSlot(EnumSlot.gem.slot, true);
			globGroup.setAllowsSlot(EnumSlot.dust.slot, true);
			globGroup.registerGlob(EnumGlob.spinatite.glob);
		}

		{
			GlobGroup<Glob> globGroup = EnumGlobGroup.MirageMaterial.globGroup;
			globGroup.setAllowsSlot(EnumSlot.ore.slot, false);
			globGroup.setAllowsSlot(EnumSlot.block.slot, true);
			globGroup.setAllowsSlot(EnumSlot.ingot.slot, true);
			globGroup.setAllowsSlot(EnumSlot.gem.slot, false);
			globGroup.setAllowsSlot(EnumSlot.dust.slot, true);
			globGroup.registerGlob(EnumGlob.spinachium.glob);
		}

	}

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
