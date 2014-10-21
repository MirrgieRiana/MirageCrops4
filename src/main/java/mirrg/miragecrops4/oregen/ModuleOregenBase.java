package mirrg.miragecrops4.oregen;

import java.util.List;

import mirrg.mir34.modding.IMod;
import mirrg.mir40.block.BlockMulti;
import mirrg.mir40.block.glob.MetablockGlob;
import mirrg.mir40.glob.GlobAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.mir40.glob.api.ISlot;
import mirrg.mir40.icon.HelpersIcon;
import mirrg.mir40.icon.MultiIcon;
import mirrg.mir40.icon.api.IMultiIconShape;
import mirrg.mir40.item.ItemMultiIcon;
import mirrg.mir40.item.glob.MetaitemIconGlob;
import mirrg.mir40.worldgen.FilterBiome;
import mirrg.mir40.worldgen.WorldGeneratorXYZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre.CountPer;
import mirrg.miragecrops4.api.oregen.ItemsOregen.IEnumGlobs;
import mirrg.miragecrops4.api.oregen.ItemsOregen.IEnumGlobsSlotProvider;
import mirrg.miragecrops4.api.oregen.RegisterMaterialColor;
import mirrg.miragecrops4.core.ModuleCore;
import mirrg.miragecrops4.core.ModuleMirageCropsBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ModuleOregenBase extends ModuleMirageCropsBase
{

	public ModuleOregenBase(IMod mod)
	{
		super(mod);
	}

	protected void createGlob(IEnumGlobs[] globs)
	{
		for (int i = 0; i < globs.length; i++) {
			globs[i].setGlob(new GlobAbstract());
		}
	}

	protected void configureBlock(Block block, String name)
	{
		configureBlock(block, name, 3.0F, 5.0F, Block.soundTypePiston);
		block.setCreativeTab(ModuleCore.creativeTab);
	}

	@Override
	protected void configureItem(Item item, String name)
	{
		super.configureItem(item, name);
		item.setCreativeTab(ModuleCore.creativeTab);
	}

	protected void createMetaBlock(IEnumGlobs[] globs, BlockMulti blockMulti, ISlot slot)
	{
		for (int i = 0; i < globs.length; i++) {
			IEnumGlobs enumGlob = globs[i];

			// グロブの設定
			((GlobAbstract) enumGlob.getGlob()).setName(((Enum<?>) enumGlob).name());

			// メタブロックの作成
			MetablockGlob metablock = new MetablockGlob(enumGlob.getGlob(), slot);

			// マルチブロックにメタブロックを登録
			blockMulti.multibase.bind(i, metablock);

			// グロブにアイテムスタックを登録
			((GlobAbstract) enumGlob.getGlob()).put(slot, new ItemStack(blockMulti, 1, i));

			// メタブロックの設定
			{
				String unlocalizedName = HelpersGlob.getDictionaryName(slot, enumGlob.getGlob());

				metablock.unlocalizedName = unlocalizedName;
				if (getMod().isClient()) {
					String textureName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
					if (HelpersIcon.getURLFromTextureName(textureName, HelpersIcon.TYPE_BLOCKS) != null) {
						metablock.iconName = textureName;
					} else {
						metablock.iconName = getMod().getModId() + ":" + "NULL_ICON";
					}
				}

				// 鉱石辞書に登録
				OreDictionary.registerOre(unlocalizedName, new ItemStack(blockMulti, 1, i));

			}

		}
	}

	protected void createMetaItem(
		List<IEnumGlobsSlotProvider[]> globsList, ItemMultiIcon itemMultiIcon,
		ISlot slot, IMultiIconShape multiIconShape)
	{
		for (int j = 0; j < globsList.size(); j++) {
			for (int i = 0; i < globsList.get(j).length; i++) {
				IEnumGlobs enumGlob = globsList.get(j)[i];

				// グロブの設定
				((GlobAbstract) enumGlob.getGlob()).setName(((Enum<?>) enumGlob).name());

				// メタアイテムの作成
				MetaitemIconGlob metaitemIcon = new MetaitemIconGlob(enumGlob.getGlob(), slot);

				// マルチブロックにメタアイテムを登録
				int id = i + j * 16;
				itemMultiIcon.multibase.bind(id, metaitemIcon);

				// グロブにアイテムスタックを登録
				((GlobAbstract) enumGlob.getGlob()).put(slot, new ItemStack(itemMultiIcon, 1, id));

				// メタアイテムの設定
				{
					String unlocalizedName = HelpersGlob.getDictionaryName(slot, enumGlob.getGlob());

					metaitemIcon.unlocalizedName = unlocalizedName;
					if (getMod().isClient()) {
						String textureName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
						if (HelpersIcon.getURLFromTextureName(textureName, HelpersIcon.TYPE_ITEMS) != null) {
							metaitemIcon.iconName = textureName;
						} else {
							metaitemIcon.multiIcon =
								new MultiIcon(multiIconShape,
									RegisterMaterialColor.instance.get(enumGlob.getGlob().getName()));
						}
					}

					// 鉱石辞書に登録
					OreDictionary.registerOre(unlocalizedName, new ItemStack(itemMultiIcon, 1, id));

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
