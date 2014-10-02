package mirrg.miragecrops4.core.oregen;

import java.util.List;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.glob.GlobAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.mir40.glob.api.ISlot;
import mirrg.mir40.icon.MultiIcon;
import mirrg.mir40.worldgen.FilterBiome;
import mirrg.mir40.worldgen.WorldGeneratorXYZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre.CountPer;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.IEnumGlobs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ModuleOregenBase extends ModuleAbstract
{

	public ModuleOregenBase(IMod mod)
	{
		super(mod);
	}

	protected BlockMulti registerBlock(BlockMulti block, Class<? extends ItemBlock> classItemBlock, String name)
	{
		GameRegistry.registerBlock(block, classItemBlock, name);
		return block;
	}

	protected ItemMulti registerItem(ItemMulti item, String name)
	{
		GameRegistry.registerItem(item, name);
		return item;
	}

	protected void createGlob(IEnumGlobs[] globs)
	{
		for (int i = 0; i < globs.length; i++) {
			globs[i].setGlob(new GlobAbstract());
		}
	}

	protected void configureBlock(Block block, String name)
	{
		block.setHardness(3.0F);
		block.setResistance(5.0F);
		block.setStepSound(Block.soundTypePiston);
		block.setBlockName(name);
		block.setBlockTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
	}

	protected void configureItem(Item item, String name)
	{
		item.setUnlocalizedName(name);
		item.setTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
	}

	protected void createMetaBlock(IEnumGlobs[] globs, BlockMulti blockMulti)
	{
		for (int i = 0; i < globs.length; i++) {
			IEnumGlobs enumGlob = globs[i];

			// グロブの設定
			((GlobAbstract) enumGlob.getGlob()).setName(((Enum) enumGlob).name());

			// メタブロックの作成
			Metablock metablock = new Metablock();

			// マルチブロックにメタブロックを登録
			blockMulti.multibase.bind(i, metablock);

			// グロブにアイテムスタックを登録
			((GlobAbstract) enumGlob.getGlob()).put(ItemsOregen.slotOre, new ItemStack(blockMulti, 1, i));

			// メタブロックの設定
			{
				String unlocalizedName = HelpersGlob.getDictionaryName(ItemsOregen.slotOre, enumGlob.getGlob());

				metablock.unlocalizedName = unlocalizedName;
				if (getMod().isClient()) {
					metablock.iconName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
				}

				// 鉱石辞書に登録
				OreDictionary.registerOre(unlocalizedName, new ItemStack(blockMulti, 1, i));

			}

		}
	}

	protected void createMetaItem(List<IEnumGlobs[]> globsList, ItemMultiIcon<MetaitemIcon> itemMultiIcon, ISlot slot)
	{
		for (int j = 0; j < globsList.size(); j++) {
			for (int i = 0; i < globsList.get(j).length; i++) {
				IEnumGlobs enumGlob = globsList.get(j)[i];

				// グロブの設定
				((GlobAbstract) enumGlob.getGlob()).setName(((Enum) enumGlob).name());

				// メタアイテムの作成
				MetaitemIcon metaitemIcon = new MetaitemIcon();

				// マルチブロックにメタアイテムを登録
				itemMultiIcon.multibase.bind(i, metaitemIcon);

				// グロブにアイテムスタックを登録
				((GlobAbstract) enumGlob.getGlob()).put(slot, new ItemStack(itemMultiIcon, 1, i));

				// メタアイテムの設定
				{
					String unlocalizedName = HelpersGlob.getDictionaryName(slot, enumGlob.getGlob());

					metaitemIcon.unlocalizedName = unlocalizedName;
					if (getMod().isClient()) {
						metaitemIcon.iconName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
						metaitemIcon.multiIcon = new MultiIcon(MultiIcons.INGOT, 0xbb5588);
					}

					// 鉱石辞書に登録
					OreDictionary.registerOre(unlocalizedName, new ItemStack(itemMultiIcon, 1, i));

				}

			}
		}
	}

	protected void registerWorldgenFromCountPerCube(int minHeight, int maxHeight, double countPerCube,
		double numberOfBlocks, ItemStack ore,
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
