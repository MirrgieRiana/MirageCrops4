package mirrg.miragecrops4.core.oregen;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.glob.GlobAbstract;
import mirrg.mir40.glob.SlotAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.mir40.worldgen.FilterBiome;
import mirrg.mir40.worldgen.WorldGeneratorXYZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre.CountPer;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMirageMagic;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMohsHardnessCrystal;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsOtherMetal;
import mirrg.miragecrops4.api.oregen.ItemsOregen.IEnumGlob;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleOregen extends ModuleAbstract
{

	public ModuleOregen(IMod mod)
	{
		super(mod);
	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		createBlocks();

		registerBlocks();

		registerWorldgen();

	}

	protected BlockMulti registerBlock(String name)
	{
		BlockMulti block = new BlockMulti();
		GameRegistry.registerBlock(block, ItemBlockMulti.class, name);
		return block;
	}

	protected void createGlob(IEnumGlob[] globs)
	{
		for (int i = 0; i < globs.length; i++) {
			globs[i].setGlob(new GlobAbstract());
		}
	}

	protected void createBlocks()
	{

		ItemsOregen.blockOreCalciteGroup = registerBlock("blockOreCalciteGroup");
		ItemsOregen.blockOreMohsHardnessCrystal = registerBlock("blockOreMohsHardnessCrystal");
		ItemsOregen.blockOreOtherMetal = registerBlock("blockOreOtherMetal");
		ItemsOregen.blockOreMirageMagic = registerBlock("blockOreMirageMagic");

		createGlob(EnumGlobsCalciteGroup.values());
		createGlob(EnumGlobsMohsHardnessCrystal.values());
		createGlob(EnumGlobsOtherMetal.values());
		createGlob(EnumGlobsMirageMagic.values());

		ItemsOregen.slotOre = new SlotAbstract();

	}

	protected void configureBlock(Block block, String name)
	{
		block
			.setHardness(3.0F)
			.setResistance(5.0F)
			.setStepSound(Block.soundTypePiston)
			.setBlockName(name)
			.setBlockTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
	}

	protected void createMetaBlock(IEnumGlob[] globs, BlockMulti blockMulti)
	{
		for (int i = 0; i < globs.length; i++) {

			// グロブの設定
			((GlobAbstract) globs[i].getGlob()).setName(((Enum) globs[i]).name());

			// メタブロックの作成
			Metablock metablock = new Metablock();

			// マルチブロックにメタブロックを登録
			blockMulti.multibase.bind(i, metablock);

			// グロブにアイテムスタックを登録
			((GlobAbstract) globs[i].getGlob()).put(ItemsOregen.slotOre,
				new ItemStack(blockMulti, 1, i));

			// メタブロックの設定
			{
				String unlocalizedName = HelpersGlob.getDictionaryName(ItemsOregen.slotOre, globs[i].getGlob());
				metablock.unlocalizedName = unlocalizedName;
				if (getMod().isClient()) {
					metablock.iconName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
				}
			}

		}
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

	protected void registerWorldgenFromCountPerCube(
		int minHeight,
		int maxHeight,
		double countPerCube,
		double numberOfBlocks,
		ItemStack ore,
		String biome)
	{
		WorldGeneratorXZOre iWorldGeneratorXY;

		iWorldGeneratorXY = new WorldGeneratorXZOre(
			CountPer.CUBE, countPerCube, minHeight, maxHeight,
			new WorldGeneratorXYZOre(numberOfBlocks, ore));
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		if (biome != null) {
			iWorldGeneratorXY.setFilter(new FilterBiome(biome));
		}

		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);
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
