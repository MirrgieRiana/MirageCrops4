package mirrg.miragecrops4.oregen;

import java.util.List;

import mirrg.mir34.modding.IMod;
import mirrg.mir40.icon.IMultiIconShape;
import mirrg.mir40.worldgen.FilterBiome;
import mirrg.mir40.worldgen.WorldGeneratorXYZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre.CountPer;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.Slot;
import mirrg.miragecrops4.api.APICore;
import mirrg.miragecrops4.lib.ModuleMirageCropsBase;
import mirrg.miragecrops4.lib.RegisterMaterialColor;
import mirrg.miragecrops4.lib.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.multi.ItemMultiIconOregen;
import mirrg.miragecrops4.lib.multi.MetablockOregen;
import mirrg.miragecrops4.lib.multi.MetaitemIconOregen;
import mirrg.miragecrops4.lib.oregen.GlobsOregen;
import mirrg.miragecrops4.lib.oregen.HelpersOregen;
import mirrg.miragecrops4.lib.oregen.ItemsOregen;
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

	protected void configureBlock(Block block, String name)
	{
		configureBlock(block, name, 3.0F, 5.0F, Block.soundTypePiston);
		block.setCreativeTab(APICore.creativeTab);
	}

	@Override
	protected void configureItem(Item item, String name)
	{
		super.configureItem(item, name);
		item.setCreativeTab(APICore.creativeTab);
	}

	protected void createMetaBlock(GlobsOregen.EnumGlobGroup enumGlobGroup, BlockMultiOregen blockMulti, Slot slot)
	{
		List<Glob> globs = enumGlobGroup.globGroup.getGlobs();

		for (int i = 0; i < globs.size(); i++) {
			Glob glob = globs.get(i);
			String unlocalizedName = gdn(slot, glob);

			// メタブロックの作成
			MetablockOregen metablock = new MetablockOregen(glob, slot);

			// マルチブロックにメタブロックを登録
			blockMulti.multi.bind(i, metablock);

			// グロブにアイテムスタックを登録
			ItemsOregen.globManager.put(unlocalizedName, new ItemStack(blockMulti, 1, i));

			// メタブロックの設定
			HelpersOregen.configureMetablock(this, metablock, unlocalizedName);

			if (enumGlobGroup.globGroup.allowsSlot(slot)) {
				// 鉱石辞書に登録
				OreDictionary.registerOre(unlocalizedName, new ItemStack(blockMulti, 1, i));
			}

		}
	}

	protected void createMetaItem(GlobsOregen.EnumGlobGroup[] enumGlobGroups, ItemMultiIconOregen itemMultiIcon,
		Slot slot,
		IMultiIconShape multiIconShape)
	{

		for (int j = 0; j < enumGlobGroups.length; j++) {
			GlobsOregen.EnumGlobGroup enumGlobGroup = enumGlobGroups[j];
			List<Glob> globs = enumGlobGroup.globGroup.getGlobs();

			for (int i = 0; i < globs.size(); i++) {
				Glob glob = globs.get(i);
				String unlocalizedName = gdn(slot, glob);

				// メタアイテムの作成
				MetaitemIconOregen metaitemIcon = new MetaitemIconOregen(glob, slot);

				// マルチブロックにメタアイテムを登録
				int id = i + j * 16;
				itemMultiIcon.multi.bind(id, metaitemIcon);

				// グロブにアイテムスタックを登録
				ItemsOregen.globManager.put(unlocalizedName, new ItemStack(itemMultiIcon, 1, id));

				// メタアイテムの設定
				HelpersOregen.configuteMetaitem(this, metaitemIcon, unlocalizedName,
					RegisterMaterialColor.instance.get(glob.getName()), multiIconShape);

				if (enumGlobGroup.globGroup.allowsSlot(slot)) {
					// 鉱石辞書に登録
					OreDictionary.registerOre(unlocalizedName, new ItemStack(itemMultiIcon, 1, id));
				}

			}
		}
	}

	protected void registerWorldgenFromCountPerCube(int minHeight, int maxHeight, double countPerCube,
		double numberOfBlocks, String biome, ItemStack ore)
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
