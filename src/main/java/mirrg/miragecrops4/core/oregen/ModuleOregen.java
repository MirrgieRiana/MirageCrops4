package mirrg.miragecrops4.core.oregen;

import mirrg.mir34.modding.IMod;
import mirrg.mir40.glob.SlotAbstract;
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
	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLPreInitializationEvent event)
	{

		MultiIcons.registerMultiIconShapes(getMod().getModId() + ":" + getModuleName() + "/multi/");

	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		createBlocks();

		createItems();

		registerBlocks();

		registerItems();

		registerWorldgen();

	}

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	protected void createBlocks()
	{

		// ブロックのインスタンス生成と登録とAPIへの代入
		ItemsOregen.blockOreCalciteGroup =
			registerBlock(new BlockMulti<Metablock>(), ItemBlockMulti.class, "blockOreCalciteGroup");
		ItemsOregen.blockOreMohsHardnessCrystal =
			registerBlock(new BlockMulti<Metablock>(), ItemBlockMulti.class, "blockOreMohsHardnessCrystal");
		ItemsOregen.blockOreOtherMetal =
			registerBlock(new BlockMulti<Metablock>(), ItemBlockMulti.class, "blockOreOtherMetal");
		ItemsOregen.blockOreMirageMagic =
			registerBlock(new BlockMulti<Metablock>(), ItemBlockMulti.class, "blockOreMirageMagic");

		// グロブのインスタンス生成とAPIへの代入
		for (IEnumGlobs[] enumGlobs : ItemsOregen.enumGlobsList) {
			createGlob(enumGlobs);
		}

		// グロブスロットのインスタンス生成とAPIへの代入
		ItemsOregen.slotOre = new SlotAbstract();

	}

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	protected void createItems()
	{

		// アイテムのインスタンス生成と登録とAPIへの代入
		ItemsOregen.itemIngot = registerItem(new ItemMultiIcon<MetaitemIcon>(), "itemIngot");
		ItemsOregen.itemGem = registerItem(new ItemMultiIcon<MetaitemIcon>(), "itemGem");

		// グロブスロットのインスタンス生成とAPIへの代入
		ItemsOregen.slotIngot = new SlotAbstract();
		ItemsOregen.slotGem = new SlotAbstract();

	}

	protected void registerBlocks()
	{

		configureBlock(ItemsOregen.blockOreCalciteGroup, "blockOreCalciteGroup");
		configureBlock(ItemsOregen.blockOreMohsHardnessCrystal, "blockOreMohsHardnessCrystal");
		configureBlock(ItemsOregen.blockOreOtherMetal, "blockOreOtherMetal");
		configureBlock(ItemsOregen.blockOreMirageMagic, "blockOreMirageMagic");

		((SlotAbstract) ItemsOregen.slotOre).setName("ore");

		// グロブ・スロットの後
		createMetaBlock(EnumGlobsCalciteGroup.values(), (BlockMulti) ItemsOregen.blockOreCalciteGroup);
		createMetaBlock(EnumGlobsMohsHardnessCrystal.values(), (BlockMulti) ItemsOregen.blockOreMohsHardnessCrystal);
		createMetaBlock(EnumGlobsOtherMetal.values(), (BlockMulti) ItemsOregen.blockOreOtherMetal);
		createMetaBlock(EnumGlobsMirageMagic.values(), (BlockMulti) ItemsOregen.blockOreMirageMagic);

	}

	protected void registerItems()
	{

		configureItem(ItemsOregen.itemIngot, "itemIngot");
		configureItem(ItemsOregen.itemGem, "itemGem");

		((SlotAbstract) ItemsOregen.slotIngot).setName("ingot");
		((SlotAbstract) ItemsOregen.slotGem).setName("gem");

		// グロブ・スロットの後
		createMetaItem(ItemsOregen.enumGlobsList,
			(ItemMultiIcon<MetaitemIcon>) ItemsOregen.itemIngot, ItemsOregen.slotIngot);
		createMetaItem(ItemsOregen.enumGlobsList,
			(ItemMultiIcon<MetaitemIcon>) ItemsOregen.itemGem, ItemsOregen.slotGem);

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

	@Override
	public String getModuleName()
	{
		return "oregen";
	}

}
