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
import mirrg.miragecrops4.lib.oregen.GlobsOregen;
import mirrg.miragecrops4.lib.oregen.GlobsOregen.EnumGlobGroup;
import mirrg.miragecrops4.lib.oregen.GlobsOregen.EnumSlot;
import mirrg.miragecrops4.lib.oregen.HelpersOregen;
import mirrg.miragecrops4.lib.oregen.ItemsOregen;
import mirrg.miragecrops4.lib.oregen.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.oregen.multi.ItemMultiIconOregen;
import mirrg.miragecrops4.lib.oregen.multi.MetablockOregen;
import mirrg.miragecrops4.lib.oregen.multi.MetaitemIconOregen;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

@SuppressWarnings({
	"rawtypes", "unchecked"
})
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
				metablock.isAllowed = true;
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
					metaitemIcon.isAllowed = true;
				}

			}
		}
	}

	public static void registerWorldgenFromCountPerCube(int minHeight, int maxHeight, double countPerCube,
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

	public static interface IHandler_eachBlock
	{

		void handle(GlobsOregen.EnumSlot enumSlot, GlobsOregen.EnumGlobGroup enumGlobGroup, String unlocalizedName);

	}

	public static void eachBlock(IHandler_eachBlock handler)
	{
		for (GlobsOregen.EnumSlot enumSlot : GlobsOregen.EnumSlot.values()) {
			if (enumSlot.type == GlobsOregen.EnumSlotType.BLOCK) {

				for (GlobsOregen.EnumGlobGroup enumGlobGroup : GlobsOregen.EnumGlobGroup.values()) {
					if (enumGlobGroup.globGroup.allowsSlot(enumSlot.slot)) {

						handler.handle(enumSlot, enumGlobGroup,
							HelpersOregen.getBlockUnlocalizedName(enumSlot.slot, enumGlobGroup.globGroup));

					}
				}

			}
		}
	}

	public static interface IHandler_eachItem
	{

		void handle(GlobsOregen.EnumSlot enumSlot, String unlocalizedName);

	}

	public static void eachItem(IHandler_eachItem handler)
	{
		for (GlobsOregen.EnumSlot enumSlot : GlobsOregen.EnumSlot.values()) {
			if (enumSlot.type == GlobsOregen.EnumSlotType.ITEM) {

				handler.handle(enumSlot,
					HelpersOregen.getItemUnlocalizedName(enumSlot.slot));

			}
		}
	}

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerBlocks()
	{
		eachBlock(new IHandler_eachBlock() {

			@Override
			public void handle(EnumSlot enumSlot, EnumGlobGroup enumGlobGroup, String unlocalizedName)
			{
				registerBlocks(enumSlot, enumGlobGroup, unlocalizedName);
			}

		});
	}

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerItems()
	{
		eachItem(new IHandler_eachItem() {

			@Override
			public void handle(EnumSlot enumSlot, String unlocalizedName)
			{
				registerItems(enumSlot, unlocalizedName);
			}

		});
	}

	@Override
	protected void configureBlocks()
	{
		eachBlock(new IHandler_eachBlock() {

			@Override
			public void handle(EnumSlot enumSlot, EnumGlobGroup enumGlobGroup, String unlocalizedName)
			{
				configureBlocks(enumSlot, enumGlobGroup, unlocalizedName);
			}

		});
	}

	@Override
	protected void configureItems()
	{
		eachItem(new IHandler_eachItem() {

			@Override
			public void handle(EnumSlot enumSlot, String unlocalizedName)
			{
				configureItems(enumSlot, unlocalizedName);
			}

		});
	}

	protected abstract void registerBlocks(EnumSlot enumSlot, EnumGlobGroup enumGlobGroup, String unlocalizedName);

	protected abstract void registerItems(EnumSlot enumSlot, String unlocalizedName);

	protected abstract void configureBlocks(EnumSlot enumSlot, EnumGlobGroup enumGlobGroup, String unlocalizedName);

	protected abstract void configureItems(EnumSlot enumSlot, String unlocalizedName);

}
