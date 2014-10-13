package mirrg.miragecrops4.oregen;

import mirrg.mir34.modding.IMod;
import mirrg.mir40.block.BlockMulti;
import mirrg.mir40.block.glob.MetablockGlob;
import mirrg.mir40.glob.SlotAbstract;
import mirrg.mir40.item.glob.MetaitemIconGlob;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMirageMagic;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMohsHardnessCrystal;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsOtherMetal;
import mirrg.miragecrops4.api.oregen.ItemsOregen.IEnumGlobs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleOregen extends ModuleOregenBase
{

	public ModuleOregen(IMod mod)
	{
		super(mod);
	}

	@Override
	public String getModuleName()
	{
		return "oregen";
	}

	//

	@Override
	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLPreInitializationEvent event)
	{

		super.handlePreClient(event);

		MultiIcons.registerMultiIconShapes(getMod().getModId() + ":" + getModuleName() + "/multi/");

	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		ItemsOregen.registerMaterialColors();

		super.handle(event);

		registerWorldgen();

	}

	//

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerBlocks()
	{

		// ブロックのインスタンス生成と登録とAPIへの代入
		ItemsOregen.blockOreCalciteGroup =
			registerBlock(new BlockMulti<MetablockGlob>(), ItemBlockMultiMirageCrops.class, "blockOreCalciteGroup");
		ItemsOregen.blockOreMohsHardnessCrystal =
			registerBlock(new BlockMulti<MetablockGlob>(), ItemBlockMultiMirageCrops.class,
				"blockOreMohsHardnessCrystal");
		ItemsOregen.blockOreOtherMetal =
			registerBlock(new BlockMulti<MetablockGlob>(), ItemBlockMultiMirageCrops.class, "blockOreOtherMetal");
		ItemsOregen.blockOreMirageMagic =
			registerBlock(new BlockMulti<MetablockGlob>(), ItemBlockMultiMirageCrops.class, "blockOreMirageMagic");

		ItemsOregen.blockCalciteGroup =
			registerBlock(new BlockMulti<MetablockGlob>(), ItemBlockMultiMirageCrops.class, "blockCalciteGroup");
		ItemsOregen.blockMohsHardnessCrystal =
			registerBlock(new BlockMulti<MetablockGlob>(), ItemBlockMultiMirageCrops.class, "blockMohsHardnessCrystal");
		ItemsOregen.blockOtherMetal =
			registerBlock(new BlockMulti<MetablockGlob>(), ItemBlockMultiMirageCrops.class, "blockOtherMetal");
		ItemsOregen.blockMirageMaterial =
			registerBlock(new BlockMulti<MetablockGlob>(), ItemBlockMultiMirageCrops.class, "blockMirageMaterial");

		// グロブのインスタンス生成とAPIへの代入
		for (IEnumGlobs[] enumGlobs : ItemsOregen.enumGlobsList) {
			createGlob(enumGlobs);
		}

		// グロブスロットのインスタンス生成とAPIへの代入
		ItemsOregen.slotOre = new SlotAbstract();
		ItemsOregen.slotBlock = new SlotAbstract();

	}

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerItems()
	{

		// アイテムのインスタンス生成と登録とAPIへの代入
		ItemsOregen.itemIngot = registerItem(new ItemMultiIconMirageCrops<MetaitemIconGlob>(), "itemIngot");
		ItemsOregen.itemGem = registerItem(new ItemMultiIconMirageCrops<MetaitemIconGlob>(), "itemGem");
		ItemsOregen.itemDust = registerItem(new ItemMultiIconMirageCrops<MetaitemIconGlob>(), "itemDust");

		// グロブスロットのインスタンス生成とAPIへの代入
		ItemsOregen.slotIngot = new SlotAbstract();
		ItemsOregen.slotGem = new SlotAbstract();
		ItemsOregen.slotDust = new SlotAbstract();

	}

	@Override
	protected void configureBlocks()
	{

		configureBlock(ItemsOregen.blockOreCalciteGroup, "blockOreCalciteGroup");
		configureBlock(ItemsOregen.blockOreMohsHardnessCrystal, "blockOreMohsHardnessCrystal");
		configureBlock(ItemsOregen.blockOreOtherMetal, "blockOreOtherMetal");
		configureBlock(ItemsOregen.blockOreMirageMagic, "blockOreMirageMagic");

		configureBlock(ItemsOregen.blockCalciteGroup, "blockCalciteGroup");
		configureBlock(ItemsOregen.blockMohsHardnessCrystal, "blockMohsHardnessCrystal");
		configureBlock(ItemsOregen.blockOtherMetal, "blockOtherMetal");
		configureBlock(ItemsOregen.blockMirageMaterial, "blockMirageMaterial");

		((SlotAbstract) ItemsOregen.slotOre).setName("ore");
		((SlotAbstract) ItemsOregen.slotBlock).setName("block");

		// グロブ・スロットの後
		createMetaBlock(EnumGlobsCalciteGroup.values(),
			(BlockMulti<MetablockGlob>) ItemsOregen.blockOreCalciteGroup, ItemsOregen.slotOre);
		createMetaBlock(EnumGlobsMohsHardnessCrystal.values(),
			(BlockMulti<MetablockGlob>) ItemsOregen.blockOreMohsHardnessCrystal, ItemsOregen.slotOre);
		createMetaBlock(EnumGlobsOtherMetal.values(),
			(BlockMulti<MetablockGlob>) ItemsOregen.blockOreOtherMetal, ItemsOregen.slotOre);
		createMetaBlock(EnumGlobsMirageMagic.values(),
			(BlockMulti<MetablockGlob>) ItemsOregen.blockOreMirageMagic, ItemsOregen.slotOre);

		createMetaBlock(EnumGlobsCalciteGroup.values(),
			(BlockMulti<MetablockGlob>) ItemsOregen.blockCalciteGroup, ItemsOregen.slotBlock);
		createMetaBlock(EnumGlobsMohsHardnessCrystal.values(),
			(BlockMulti<MetablockGlob>) ItemsOregen.blockMohsHardnessCrystal, ItemsOregen.slotBlock);
		createMetaBlock(EnumGlobsOtherMetal.values(),
			(BlockMulti<MetablockGlob>) ItemsOregen.blockOtherMetal, ItemsOregen.slotBlock);
		createMetaBlock(EnumGlobsMirageMagic.values(),
			(BlockMulti<MetablockGlob>) ItemsOregen.blockMirageMaterial, ItemsOregen.slotBlock);

	}

	@Override
	protected void configureItems()
	{

		configureItem(ItemsOregen.itemIngot, "itemIngot");
		configureItem(ItemsOregen.itemGem, "itemGem");
		configureItem(ItemsOregen.itemDust, "itemDust");

		((SlotAbstract) ItemsOregen.slotIngot).setName("ingot");
		((SlotAbstract) ItemsOregen.slotGem).setName("gem");
		((SlotAbstract) ItemsOregen.slotDust).setName("dust");

		// グロブ・スロットの後
		createMetaItem(ItemsOregen.enumGlobsList,
			(ItemMultiIconMirageCrops<MetaitemIconGlob>) ItemsOregen.itemIngot, ItemsOregen.slotIngot, MultiIcons.INGOT);
		createMetaItem(ItemsOregen.enumGlobsList,
			(ItemMultiIconMirageCrops<MetaitemIconGlob>) ItemsOregen.itemGem, ItemsOregen.slotGem, MultiIcons.GEM);
		createMetaItem(ItemsOregen.enumGlobsList,
			(ItemMultiIconMirageCrops<MetaitemIconGlob>) ItemsOregen.itemDust, ItemsOregen.slotDust, MultiIcons.DUST);

	}

	/**
	 * registerBlocksの後
	 */
	protected void registerWorldgen()
	{
		int minHeight;
		int maxHeight;
		double countPerCube;
		double numberOfBlocks;
		ItemStack ore;
		String biome;

		minHeight = 0;
		maxHeight = 128;
		countPerCube = 0.4;
		numberOfBlocks = 32;
		ore = EnumGlobsCalciteGroup.calcite.glob.copy(ItemsOregen.slotOre);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//

		minHeight = 0;
		maxHeight = 96;
		countPerCube = 1.8;
		numberOfBlocks = 8;
		ore = EnumGlobsCalciteGroup.magnesite.glob.copy(ItemsOregen.slotOre);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 80;
		countPerCube = 1.6;
		numberOfBlocks = 7;
		ore = EnumGlobsCalciteGroup.siderite.glob.copy(ItemsOregen.slotOre);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 64;
		countPerCube = 1.4;
		numberOfBlocks = 6;
		ore = EnumGlobsCalciteGroup.smithsonite.glob.copy(ItemsOregen.slotOre);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//

		minHeight = 0;
		maxHeight = 48;
		countPerCube = 0.9;
		numberOfBlocks = 6;
		ore = EnumGlobsCalciteGroup.rhodochrosite.glob.copy(ItemsOregen.slotOre);
		biome = "ocean";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 32;
		countPerCube = 0.7;
		numberOfBlocks = 5;
		ore = EnumGlobsCalciteGroup.sphaerocobaltite.glob.copy(ItemsOregen.slotOre);
		biome = "forest";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 24;
		countPerCube = 0.5;
		numberOfBlocks = 4;
		ore = EnumGlobsCalciteGroup.gaspeite.glob.copy(ItemsOregen.slotOre);
		biome = "desert";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//

		minHeight = 0;
		maxHeight = 16;
		countPerCube = 0.5;
		numberOfBlocks = 1;
		ore = EnumGlobsCalciteGroup.otavite.glob.copy(ItemsOregen.slotOre);
		biome = "extreme";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//
		//

		minHeight = 0;
		maxHeight = 60;
		countPerCube = 0.1;
		numberOfBlocks = 4;
		ore = EnumGlobsOtherMetal.bismuth.glob.copy(ItemsOregen.slotOre);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 60;
		countPerCube = 0.3;
		numberOfBlocks = 8;
		ore = EnumGlobsMirageMagic.spinatite.glob.copy(ItemsOregen.slotOre);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//
		//

		minHeight = 64;
		maxHeight = 128;
		countPerCube = 0.8;
		numberOfBlocks = 18;
		ore = EnumGlobsMohsHardnessCrystal.apatite.glob.copy(ItemsOregen.slotOre);
		biome = "extreme";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 64;
		maxHeight = 128;
		countPerCube = 1.5;
		numberOfBlocks = 8;
		ore = EnumGlobsMohsHardnessCrystal.fluorite.glob.copy(ItemsOregen.slotOre);
		biome = "extreme";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

	}

}
