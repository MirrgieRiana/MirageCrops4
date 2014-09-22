package mirrg.miragecrops4.core.oregen;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.glob.GlobAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.mir40.worldgen.FilterBiome;
import mirrg.mir40.worldgen.WorldGeneratorXYZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre.CountPer;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.IEnumGlobs;

public abstract class ModuleOregenBase extends ModuleAbstract
{

	public ModuleOregenBase(IMod mod)
	{
		super(mod);
	}

	protected BlockMulti registerBlock(String name)
	{
		BlockMulti block = new BlockMulti();
		GameRegistry.registerBlock(block, ItemBlockMulti.class, name);
		return block;
	}

	protected void createGlob(IEnumGlobs[] globs)
	{
		for (int i = 0; i < globs.length; i++) {
			globs[i].setGlob(new GlobAbstract());
		}
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

	protected void createMetaBlock(IEnumGlobs[] globs, BlockMulti blockMulti)
	{
		for (int i = 0; i < globs.length; i++) {

			// グロブの設定
			((GlobAbstract) globs[i].getGlob()).setName(((Enum) globs[i]).name());

			// メタブロックの作成
			Metablock metablock = new Metablock();

			// マルチブロックにメタブロックを登録
			blockMulti.multibase.bind(i, metablock);

			// グロブにアイテムスタックを登録
			((GlobAbstract) globs[i].getGlob()).put(ItemsOregen.slotOre, new ItemStack(blockMulti, 1, i));

			// メタブロックの設定
			{
				String unlocalizedName = HelpersGlob.getDictionaryName(ItemsOregen.slotOre, globs[i].getGlob());

				metablock.unlocalizedName = unlocalizedName;
				if (getMod().isClient()) {
					metablock.iconName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
				}

				// 鉱石辞書に登録
				OreDictionary.registerOre(unlocalizedName, new ItemStack(blockMulti, 1, i));

			}

		}
	}

	protected void registerWorldgenFromCountPerCube(int minHeight, int maxHeight, double countPerCube, double numberOfBlocks, ItemStack ore,
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

}
