package mirrg.miragecrops4.oregen.global;

import mirrg.mir40.icon.api.IMultiIconShape;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobGroup;
import mirrg.mir41.glob.GlobManager;
import mirrg.mir41.glob.Slot;
import mirrg.miragecrops4.oregen.ModuleOregen;
import mirrg.miragecrops4.oregen.global.MultiIcons.MultiIconShapes;
import mirrg.miragecrops4.oregen.multi.BlockMultiMirageCrops;
import mirrg.miragecrops4.oregen.multi.ItemMultiIconMirageCrops;

public class ItemsOregen
{

	public static ModuleOregen moduleOregen;

	public static GlobManager<Slot, Glob> globManager = new GlobManager<Slot, Glob>();

	public static enum EnumSlotType
	{
		BLOCK, ITEM,
	}

	public static enum Slots
	{
		ore(EnumSlotType.BLOCK, null),
		block(EnumSlotType.BLOCK, null),
		ingot(EnumSlotType.ITEM, MultiIcons.MultiIconShapes.INGOT),
		gem(EnumSlotType.ITEM, MultiIcons.MultiIconShapes.GEM),
		dust(EnumSlotType.ITEM, MultiIcons.MultiIconShapes.DUST), ;

		public final Slot slot;
		public final EnumSlotType type;
		public final MultiIconShapes enumMultiIconShape;

		private Slots(EnumSlotType type, MultiIconShapes enumMultiIconShape)
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

	public static enum Globs
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

		private Globs()
		{
			glob = new Glob(name());
		}

	}

	public static enum GlobGroups
	{
		CalciteGroup,
		MohsHardnessCrystal,
		OtherMetal,
		MirageMagic,
		MirageMaterial, ;

		public final GlobGroup<Glob> globGroup;

		private GlobGroups()
		{
			globGroup = new GlobGroup<Glob>(name());
		}

	}

	static
	{
		{
			GlobGroup<Glob> globGroup = GlobGroups.CalciteGroup.globGroup;
			globGroup.setAllowsSlot(Slots.ore.slot, true);
			globGroup.setAllowsSlot(Slots.block.slot, true);
			globGroup.setAllowsSlot(Slots.ingot.slot, false);
			globGroup.setAllowsSlot(Slots.gem.slot, true);
			globGroup.setAllowsSlot(Slots.dust.slot, true);
			globGroup.registerGlob(Globs.calcite.glob);
			globGroup.registerGlob(Globs.magnesite.glob);
			globGroup.registerGlob(Globs.siderite.glob);
			globGroup.registerGlob(Globs.rhodochrosite.glob);
			globGroup.registerGlob(Globs.smithsonite.glob);
			globGroup.registerGlob(Globs.sphaerocobaltite.glob);
			globGroup.registerGlob(Globs.gaspeite.glob);
			globGroup.registerGlob(Globs.otavite.glob);
		}

		{
			GlobGroup<Glob> globGroup = GlobGroups.MohsHardnessCrystal.globGroup;
			globGroup.setAllowsSlot(Slots.ore.slot, true);
			globGroup.setAllowsSlot(Slots.block.slot, true);
			globGroup.setAllowsSlot(Slots.ingot.slot, false);
			globGroup.setAllowsSlot(Slots.gem.slot, true);
			globGroup.setAllowsSlot(Slots.dust.slot, true);
			globGroup.registerGlob(Globs.talc.glob);
			globGroup.registerGlob(Globs.gypsum.glob);
			globGroup.registerGlob(Globs.calcite.glob);
			globGroup.registerGlob(Globs.fluorite.glob);
			globGroup.registerGlob(Globs.apatite.glob);
			globGroup.registerGlob(Globs.orthoclase.glob);
			globGroup.registerGlob(Globs.certusQuartz.glob);
			globGroup.registerGlob(Globs.topaz.glob);
			globGroup.registerGlob(Globs.ruby.glob);
			globGroup.registerGlob(Globs.diamond.glob);
		}

		{
			GlobGroup<Glob> globGroup = GlobGroups.OtherMetal.globGroup;
			globGroup.setAllowsSlot(Slots.ore.slot, true);
			globGroup.setAllowsSlot(Slots.block.slot, true);
			globGroup.setAllowsSlot(Slots.ingot.slot, true);
			globGroup.setAllowsSlot(Slots.gem.slot, false);
			globGroup.setAllowsSlot(Slots.dust.slot, true);
			globGroup.registerGlob(Globs.bismuth.glob);
		}

		{
			GlobGroup<Glob> globGroup = GlobGroups.MirageMagic.globGroup;
			globGroup.setAllowsSlot(Slots.ore.slot, true);
			globGroup.setAllowsSlot(Slots.block.slot, false);
			globGroup.setAllowsSlot(Slots.ingot.slot, false);
			globGroup.setAllowsSlot(Slots.gem.slot, true);
			globGroup.setAllowsSlot(Slots.dust.slot, true);
			globGroup.registerGlob(Globs.spinatite.glob);
		}

		{
			GlobGroup<Glob> globGroup = GlobGroups.MirageMaterial.globGroup;
			globGroup.setAllowsSlot(Slots.ore.slot, false);
			globGroup.setAllowsSlot(Slots.block.slot, true);
			globGroup.setAllowsSlot(Slots.ingot.slot, true);
			globGroup.setAllowsSlot(Slots.gem.slot, false);
			globGroup.setAllowsSlot(Slots.dust.slot, true);
			globGroup.registerGlob(Globs.spinachium.glob);
		}

	}

	public static BlockMultiMirageCrops.Raw blockOreCalciteGroup;
	public static BlockMultiMirageCrops.Raw blockOreMohsHardnessCrystal;
	public static BlockMultiMirageCrops.Raw blockOreOtherMetal;
	public static BlockMultiMirageCrops.Raw blockOreMirageMagic;

	public static BlockMultiMirageCrops.Raw blockCalciteGroup;
	public static BlockMultiMirageCrops.Raw blockMohsHardnessCrystal;
	public static BlockMultiMirageCrops.Raw blockOtherMetal;
	public static BlockMultiMirageCrops.Raw blockMirageMaterial;

	public static ItemMultiIconMirageCrops.Raw itemIngot;
	public static ItemMultiIconMirageCrops.Raw itemGem;
	public static ItemMultiIconMirageCrops.Raw itemDust;

}
