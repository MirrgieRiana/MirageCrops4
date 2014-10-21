package mirrg.miragecrops4.api.oregen;

import mirrg.mir40.icon.api.IMultiIconShape;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobGroup;
import mirrg.mir41.glob.GlobManager;
import mirrg.mir41.glob.Slot;
import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.ISlot;
import mirrg.miragecrops4.oregen.MultiIcons;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemsOregen
{

	public static GlobManager<ISlot, IGlob> globManager = new GlobManager<ISlot, IGlob>();

	public static enum EnumSlotType
	{
		BLOCK, ITEM,
	}

	public static enum Slots
	{
		ore(EnumSlotType.BLOCK, null),
		block(EnumSlotType.BLOCK, null),
		ingot(EnumSlotType.ITEM, MultiIcons.INGOT),
		gem(EnumSlotType.ITEM, MultiIcons.GEM),
		dust(EnumSlotType.ITEM, MultiIcons.DUST), ;

		public final Slot slot;
		public final EnumSlotType type;
		public final IMultiIconShape icon;

		private Slots(EnumSlotType type, IMultiIconShape icon)
		{
			slot = new Slot(name());
			this.type = type;
			this.icon = icon;
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

		public final GlobGroup<IGlob> globGroup;

		private GlobGroups()
		{
			globGroup = new GlobGroup<IGlob>(name());
		}

	}

	static
	{
		{
			GlobGroup<IGlob> globGroup = GlobGroups.CalciteGroup.globGroup;
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
			GlobGroup<IGlob> globGroup = GlobGroups.MohsHardnessCrystal.globGroup;
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
			GlobGroup<IGlob> globGroup = GlobGroups.OtherMetal.globGroup;
			globGroup.setAllowsSlot(Slots.ore.slot, true);
			globGroup.setAllowsSlot(Slots.block.slot, true);
			globGroup.setAllowsSlot(Slots.ingot.slot, true);
			globGroup.setAllowsSlot(Slots.gem.slot, false);
			globGroup.setAllowsSlot(Slots.dust.slot, true);
			globGroup.registerGlob(Globs.bismuth.glob);
		}

		{
			GlobGroup<IGlob> globGroup = GlobGroups.MirageMagic.globGroup;
			globGroup.setAllowsSlot(Slots.ore.slot, true);
			globGroup.setAllowsSlot(Slots.block.slot, false);
			globGroup.setAllowsSlot(Slots.ingot.slot, false);
			globGroup.setAllowsSlot(Slots.gem.slot, true);
			globGroup.setAllowsSlot(Slots.dust.slot, true);
			globGroup.registerGlob(Globs.spinatite.glob);
		}

		{
			GlobGroup<IGlob> globGroup = GlobGroups.MirageMaterial.globGroup;
			globGroup.setAllowsSlot(Slots.ore.slot, false);
			globGroup.setAllowsSlot(Slots.block.slot, true);
			globGroup.setAllowsSlot(Slots.ingot.slot, true);
			globGroup.setAllowsSlot(Slots.gem.slot, false);
			globGroup.setAllowsSlot(Slots.dust.slot, true);
			globGroup.registerGlob(Globs.spinachium.glob);
		}

	}

	public static Block blockOreCalciteGroup;
	public static Block blockOreMohsHardnessCrystal;
	public static Block blockOreOtherMetal;
	public static Block blockOreMirageMagic;

	public static Block blockCalciteGroup;
	public static Block blockMohsHardnessCrystal;
	public static Block blockOtherMetal;
	public static Block blockMirageMaterial;

	public static Item itemIngot;
	public static Item itemGem;
	public static Item itemDust;

	public static void registerMaterialColor(String name, int color)
	{
		RegisterMaterialColor.instance.put(name, color);
	}

	public static void registerMaterialColors()
	{

		registerMaterialColor("calcite", 0xDBE0BE);
		registerMaterialColor("magnesite", 0xDAE1F0);
		registerMaterialColor("siderite", 0x91A42C);
		registerMaterialColor("rhodochrosite", 0xE594BB);
		registerMaterialColor("smithsonite", 0x97DDBF);
		registerMaterialColor("sphaerocobaltite", 0xC0278B);
		registerMaterialColor("gaspeite", 0x406F2F);
		registerMaterialColor("otavite", 0x95ABE8);

		registerMaterialColor("calcium", 0xE0E0D5);
		registerMaterialColor("magnesium", 0xD8A9A9);
		registerMaterialColor("iron", 0xA5A5A5);
		registerMaterialColor("manganese", 0xC8C0C0);
		registerMaterialColor("zinc", 0xE5DBF2);
		registerMaterialColor("cobalt", 0x4242CF);
		registerMaterialColor("nickel", 0x96A4FF);
		registerMaterialColor("cadmium", 0x2F2F38);

		registerMaterialColor("glass", 0xEEEEEE);

		registerMaterialColor("bismuth", 0x597ABA);
		registerMaterialColor("spinatite", 0x137F18);
		registerMaterialColor("spinachium", 0x00C610);
		registerMaterialColor("mirageAlloy", 0x548327);

		registerMaterialColor("talc", 0xD3E2D7);
		registerMaterialColor("gypsum", 0xEAEAEA);
		registerMaterialColor("fluorite", 0x1BE86A);
		registerMaterialColor("apatite", 0x5BB5FF);
		registerMaterialColor("orthoclase", 0xFFE2B7);
		registerMaterialColor("certusQuartz", 0xC6D0FF);
		registerMaterialColor("topaz", 0xFFC46D);
		registerMaterialColor("ruby", 0xE40000);
		registerMaterialColor("diamond", 0x33EBCB);

		registerMaterialColor("sulfur", 0xFFE727);

		registerMaterialColor("copper", 0xEF7351);
		registerMaterialColor("tin", 0xCEDBEA);
		registerMaterialColor("gold", 0xFFFF0B);
		registerMaterialColor("silver", 0xD8F4FF);
		registerMaterialColor("lead", 0x5929AD);
		registerMaterialColor("bronze", 0xFF6A00);
		registerMaterialColor("brass", 0xEABE2E);
		registerMaterialColor("electrum", 0xFFFF8C);
		registerMaterialColor("mercury", 0xD5B7B7);
		registerMaterialColor("titanium", 0xD197FF);
		registerMaterialColor("chrome", 0xFFAAD5);
		registerMaterialColor("iridium", 0xE0FFE0);
		registerMaterialColor("osmium", 0x4B00CE);
		registerMaterialColor("steel", 0x74749E);
		registerMaterialColor("stainlessSteel", 0x9EEDBB);
		registerMaterialColor("tungsten", 0x333346);
		registerMaterialColor("tungstenSteel", 0x3636A0);
		registerMaterialColor("platinum", 0xFFF6DD);
		registerMaterialColor("aluminium", 0xC0D3F7);

	}

}
