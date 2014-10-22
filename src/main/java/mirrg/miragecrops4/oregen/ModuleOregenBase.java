package mirrg.miragecrops4.oregen;

import java.util.List;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.IModule;
import mirrg.mir40.icon.HelpersIcon;
import mirrg.mir40.icon.MultiIcon;
import mirrg.mir40.icon.api.IMultiIconShape;
import mirrg.mir40.reflect.HelpersReflect;
import mirrg.mir40.worldgen.FilterBiome;
import mirrg.mir40.worldgen.WorldGeneratorXYZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre;
import mirrg.mir40.worldgen.WorldGeneratorXZOre.CountPer;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.Slot;
import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.IGlobGroup;
import mirrg.mir41.glob.api.ISlot;
import mirrg.miragecrops4.core.ModuleCore;
import mirrg.miragecrops4.core.ModuleMirageCropsBase;
import mirrg.miragecrops4.oregen.global.HelpersModuleOregen;
import mirrg.miragecrops4.oregen.global.ItemsOregen;
import mirrg.miragecrops4.oregen.global.ItemsOregen.GlobGroups;
import mirrg.miragecrops4.oregen.global.RegisterMaterialColor;
import mirrg.miragecrops4.oregen.multi.BlockMultiMirageCrops;
import mirrg.miragecrops4.oregen.multi.ItemMultiIconMirageCrops;
import mirrg.miragecrops4.oregen.multi.MetablockGlob;
import mirrg.miragecrops4.oregen.multi.MetaitemIconGlob;
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

	protected void createMetaBlock(GlobGroups enumGlobGroup, BlockMultiMirageCrops.Raw blockMulti, Slot slot)
	{
		List<Glob> globs = enumGlobGroup.globGroup.getGlobs();

		for (int i = 0; i < globs.size(); i++) {
			Glob glob = globs.get(i);
			String unlocalizedName = gdn(slot, glob);

			// メタブロックの作成
			MetablockGlob.Raw metablock = new MetablockGlob.Raw(glob, slot);

			// マルチブロックにメタブロックを登録
			blockMulti.multibase.bind(i, metablock);

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

	public static void overrideMetablock(ISlot slot, IGlobGroup<?> globGroup, IGlob glob, MetablockGlob.Raw metablock)
	{
		Object blockMulti = HelpersReflect.getStaticField(
			ItemsOregen.class, HelpersModuleOregen.getBlockUnlocalizedName(slot, globGroup));
		if (blockMulti == null || blockMulti instanceof Exception) {
			throw new RuntimeException((Exception) blockMulti);
		}

		((BlockMultiMirageCrops.Raw) blockMulti).multibase.bindForce(
			globGroup.getGlobs().indexOf(glob), metablock);
	}

	public static void configureMetablock(IModule module,
		MetablockGlob.Raw metablock, String unlocalizedName)
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

	protected void createMetaItem(GlobGroups[] enumGlobGroups, ItemMultiIconMirageCrops.Raw itemMultiIcon, Slot slot,
		IMultiIconShape multiIconShape)
	{

		for (int j = 0; j < enumGlobGroups.length; j++) {
			GlobGroups enumGlobGroup = enumGlobGroups[j];
			List<Glob> globs = enumGlobGroup.globGroup.getGlobs();

			for (int i = 0; i < globs.size(); i++) {
				Glob glob = globs.get(i);
				String unlocalizedName = gdn(slot, glob);

				// メタアイテムの作成
				MetaitemIconGlob.Raw metaitemIcon = new MetaitemIconGlob.Raw(glob, slot);

				// マルチブロックにメタアイテムを登録
				int id = i + j * 16;
				itemMultiIcon.multibase.bind(id, metaitemIcon);

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
		MetaitemIconGlob.Raw metaitemIcon, String unlocalizedName, int color, IMultiIconShape multiIconShape)
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
