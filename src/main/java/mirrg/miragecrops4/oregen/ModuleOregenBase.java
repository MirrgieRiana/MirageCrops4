package mirrg.miragecrops4.oregen;

import java.util.List;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.IModule;
import mirrg.mir40.icon.HelpersIcon;
import mirrg.mir40.icon.IMultiIconShape;
import mirrg.mir40.icon.MultiIcon;
import mirrg.mir40.reflect.HelpersReflect;
import mirrg.mir40.worldgen.FilterBiome;
import mirrg.mir40.worldgen.WorldGeneratorXYZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre.CountPer;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.IGlob;
import mirrg.mir41.glob.IGlobGroup;
import mirrg.mir41.glob.ISlot;
import mirrg.mir41.glob.Slot;
import mirrg.miragecrops4.core.ModuleCore;
import mirrg.miragecrops4.lib.ModuleMirageCropsBase;
import mirrg.miragecrops4.lib.RegisterMaterialColor;
import mirrg.miragecrops4.lib.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.multi.ItemMultiIconOregen;
import mirrg.miragecrops4.lib.multi.MetablockOregen;
import mirrg.miragecrops4.lib.multi.MetaitemIconOregen;
import mirrg.miragecrops4.oregen.global.GlobsOregen;
import mirrg.miragecrops4.oregen.global.GlobsOregen.EnumGlobGroup;
import mirrg.miragecrops4.oregen.global.ItemsOregen;
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
		block.setCreativeTab(ModuleCore.creativeTab);
	}

	@Override
	protected void configureItem(Item item, String name)
	{
		super.configureItem(item, name);
		item.setCreativeTab(ModuleCore.creativeTab);
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
			configureMetablock(this, metablock, unlocalizedName);

			if (enumGlobGroup.globGroup.allowsSlot(slot)) {
				// 鉱石辞書に登録
				OreDictionary.registerOre(unlocalizedName, new ItemStack(blockMulti, 1, i));
			}

		}
	}

	public static void overrideMetablock(ISlot slot, IGlobGroup<?> globGroup, IGlob glob, MetablockOregen metablock)
	{
		Object blockMulti = HelpersReflect.getStaticField(
			ItemsOregen.class, ItemsOregen.getBlockUnlocalizedName(slot, globGroup));
		if (blockMulti == null || blockMulti instanceof Exception) {
			throw new RuntimeException((Exception) blockMulti);
		}

		((BlockMultiOregen) blockMulti).multi.clearBindind(globGroup.getGlobs().indexOf(glob));
		((BlockMultiOregen) blockMulti).multi.bind(globGroup.getGlobs().indexOf(glob), metablock);
	}

	public static void configureMetablock(IModule module,
		MetablockOregen metablock, String unlocalizedName)
	{
		metablock.unlocalizedName = unlocalizedName;
		if (module.getMod().isClient()) {
			String textureName =
				module.getMod().getModId() + ":" + module.getModuleName() + "/" + unlocalizedName;
			if (HelpersIcon.getURLFromTextureName(textureName, HelpersIcon.TYPE_BLOCKS) != null) {
				metablock.iconName = textureName;
			} else {
				metablock.iconName = module.getMod().getModId() + ":" + "NULL_ICON";
			}
		}
	}

	protected void createMetaItem(GlobsOregen.EnumGlobGroup[] enumGlobGroups, ItemMultiIconOregen itemMultiIcon, Slot slot,
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
				configuteMetaitem(this, metaitemIcon, unlocalizedName,
					RegisterMaterialColor.instance.get(glob.getName()), multiIconShape);

				if (enumGlobGroup.globGroup.allowsSlot(slot)) {
					// 鉱石辞書に登録
					OreDictionary.registerOre(unlocalizedName, new ItemStack(itemMultiIcon, 1, id));
				}

			}
		}
	}

	public static void configuteMetaitem(IModule module,
		MetaitemIconOregen metaitemIcon, String unlocalizedName, int color, IMultiIconShape multiIconShape)
	{
		metaitemIcon.unlocalizedName = unlocalizedName;
		if (module.getMod().isClient()) {
			String textureName =
				module.getMod().getModId() + ":" + module.getModuleName() + "/" + unlocalizedName;
			if (HelpersIcon.getURLFromTextureName(textureName, HelpersIcon.TYPE_ITEMS) != null) {
				metaitemIcon.iconName = textureName;
			} else {
				metaitemIcon.multiIcon = new MultiIcon(multiIconShape, color);
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
