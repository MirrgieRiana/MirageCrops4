package mirrg.miragecrops4.core.oregen;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.glob.GlobAbstract;
import mirrg.mir40.glob.SlotAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.mir40.worldgen.FilterBiome;
import mirrg.mir40.worldgen.IWorldGeneratorXZ;
import mirrg.mir40.worldgen.WorldGeneratorXYZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre.CountPer;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMirageMagic;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMohsHardnessCrystal;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsOtherMetal;
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

	protected void createBlocks()
	{

		{
			BlockMulti block = new BlockMulti();
			String name = "blockOreCalciteGroup";
			GameRegistry.registerBlock(block, ItemBlockMulti.class, name);
			ItemsOregen.blockOreCalciteGroup = block;
		}

		{
			BlockMulti block = new BlockMulti();
			String name = "blockOreMohsHardnessCrystal";
			GameRegistry.registerBlock(block, ItemBlockMulti.class, name);
			ItemsOregen.blockOreMohsHardnessCrystal = block;
		}

		{
			EnumGlobsCalciteGroup[] globs = EnumGlobsCalciteGroup.values();
			for (int i = 0; i < globs.length; i++) {
				globs[i].glob = new GlobAbstract();
			}
		}

		{
			EnumGlobsMohsHardnessCrystal[] globs = EnumGlobsMohsHardnessCrystal.values();
			for (int i = 0; i < globs.length; i++) {
				globs[i].glob = new GlobAbstract();
			}
		}

		ItemsOregen.slotOre = new SlotAbstract();

	}

	protected void registerBlocks()
	{

		ItemsOregen.blockOreCalciteGroup
			.setHardness(3.0F)
			.setResistance(5.0F)
			.setStepSound(Block.soundTypePiston)
			.setBlockName("blockOreCalciteGroup")
			.setBlockTextureName(getMod().getModId() + ":" + getModuleName() + "/" + "blockOreMohsHardnessCrystal");

		ItemsOregen.blockOreMohsHardnessCrystal
			.setHardness(3.0F)
			.setResistance(5.0F)
			.setStepSound(Block.soundTypePiston)
			.setBlockName("blockOreMohsHardnessCrystal")
			.setBlockTextureName(getMod().getModId() + ":" + getModuleName() + "/" + "blockOreMohsHardnessCrystal");

		{
			EnumGlobsCalciteGroup[] globs = EnumGlobsCalciteGroup.values();
			for (int i = 0; i < globs.length; i++) {

				// メタブロックの作成
				Metablock metablock = new Metablock();

				// マルチブロックにメタブロックを登録
				((BlockMulti) ItemsOregen.blockOreCalciteGroup).multibase.bind(i, metablock);

				// グロブにアイテムスタックを登録
				((GlobAbstract) globs[i].glob).put(ItemsOregen.slotOre,
					new ItemStack(ItemsOregen.blockOreCalciteGroup, 1, i));

				// メタブロックの設定
				{
					String unlocalizedName = HelpersGlob.getDictionaryName(globs[i].glob, ItemsOregen.slotOre);
					metablock.unlocalizedName = unlocalizedName;
					metablock.iconName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
				}

				// グロブの設定
				((GlobAbstract) globs[i].glob).setName(globs[i].name());
			}

		}

		{
			EnumGlobsMohsHardnessCrystal[] globs = EnumGlobsMohsHardnessCrystal.values();
			for (int i = 0; i < globs.length; i++) {

				// メタブロックの作成
				Metablock metablock = new Metablock();

				// マルチブロックにメタブロックを登録
				((BlockMulti) ItemsOregen.blockOreMohsHardnessCrystal).multibase.bind(i, metablock);

				// グロブにアイテムスタックを登録
				((GlobAbstract) globs[i].glob).put(ItemsOregen.slotOre,
					new ItemStack(ItemsOregen.blockOreMohsHardnessCrystal, 1, i));

				// メタブロックの設定
				{
					String unlocalizedName = HelpersGlob.getDictionaryName(globs[i].glob, ItemsOregen.slotOre);
					metablock.unlocalizedName = unlocalizedName;
					metablock.iconName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
				}

				// グロブの設定
				((GlobAbstract) globs[i].glob).setName(globs[i].name());
			}

		}

		((SlotAbstract) ItemsOregen.slotOre).setName("ore");

	}

	/**
	 * registerBlocksの後
	 */
	protected void registerWorldgen()
	{
		IWorldGeneratorXZ iWorldGeneratorXY;
		double blocksPerCube;

		blocksPerCube = 
		iWorldGeneratorXY = new WorldGeneratorXZOre(CountPer.CUBE, 1.0, 0, 128,
			new WorldGeneratorXYZOre(8, EnumGlobsCalciteGroup.calcite.glob.copy(ItemsOregen.slotOre)));
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		//

		iWorldGeneratorXY = new WorldGeneratorXZOre(CountPer.CUBE, 1.4, 0, 96,
			new WorldGeneratorXYZOre(4, EnumGlobsCalciteGroup.magnesite.glob.copy(ItemsOregen.slotOre)));
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		iWorldGeneratorXY = new WorldGeneratorXZOre(CountPer.CUBE, 1.2, 0, 80,
			new WorldGeneratorXYZOre(3.5, EnumGlobsCalciteGroup.siderite.glob.copy(ItemsOregen.slotOre)));
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		iWorldGeneratorXY = new WorldGeneratorXZOre(CountPer.CUBE, 1.0, 0, 64,
			new WorldGeneratorXYZOre(3, EnumGlobsCalciteGroup.smithsonite.glob.copy(ItemsOregen.slotOre)));
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		//

		iWorldGeneratorXY = new FilterBiome(new WorldGeneratorXZOre(CountPer.CUBE, 0.6, 0, 48,
			new WorldGeneratorXYZOre(2.5, EnumGlobsCalciteGroup.rhodochrosite.glob.copy(ItemsOregen.slotOre))),
			"ocean");
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		iWorldGeneratorXY = new FilterBiome(new WorldGeneratorXZOre(CountPer.CUBE, 0.5, 0, 32,
			new WorldGeneratorXYZOre(2, EnumGlobsCalciteGroup.sphaerocobaltite.glob.copy(ItemsOregen.slotOre))),
			"forest");
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		iWorldGeneratorXY = new FilterBiome(new WorldGeneratorXZOre(CountPer.CUBE, 0.4, 0, 24,
			new WorldGeneratorXYZOre(2, EnumGlobsCalciteGroup.gaspeite.glob.copy(ItemsOregen.slotOre))),
			"desert");
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		//

		iWorldGeneratorXY = new FilterBiome(new WorldGeneratorXZOre(CountPer.CUBE, 0.2, 0, 16,
			new WorldGeneratorXYZOre(1.5, EnumGlobsCalciteGroup.otavite.glob.copy(ItemsOregen.slotOre))),
			"extreme");
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		//
		//

		iWorldGeneratorXY = new WorldGeneratorXZOre(CountPer.CUBE, 2.0, 0, 60,
			new WorldGeneratorXYZOre(4, EnumGlobsOtherMetal.bismuth.glob.copy(ItemsOregen.slotOre)));
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		iWorldGeneratorXY = new WorldGeneratorXZOre(CountPer.CUBE, 8.0, 0, 120,
			new WorldGeneratorXYZOre(4, EnumGlobsMirageMagic.spinatite.glob.copy(ItemsOregen.slotOre)));
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		//
		//

		iWorldGeneratorXY = new FilterBiome(new WorldGeneratorXZOre(CountPer.CUBE, 1.0, 64, 128,
			new WorldGeneratorXYZOre(8, EnumGlobsMohsHardnessCrystal.apatite.glob.copy(ItemsOregen.slotOre))),
			"extreme");
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

		iWorldGeneratorXY = new FilterBiome(new WorldGeneratorXZOre(CountPer.CUBE, 1.0, 64, 128,
			new WorldGeneratorXYZOre(4, EnumGlobsMohsHardnessCrystal.fluorite.glob.copy(ItemsOregen.slotOre))),
			"extreme");
		GameRegistry.registerWorldGenerator(iWorldGeneratorXY, 801);

	}

	@Override
	public String getModuleName()
	{
		return "oregen";
	}

}
