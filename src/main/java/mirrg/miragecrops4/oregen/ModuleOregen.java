package mirrg.miragecrops4.oregen;

import mirrg.mir34.modding.IMod;
import mirrg.mir40.block.BlockMulti;
import mirrg.mir40.glob.SlotAbstract;
import mirrg.mir40.glob.api.ISlot;
import mirrg.mir40.math.HelpersString;
import mirrg.mir40.reflect.HelpersReflect;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMirageMagic;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMohsHardnessCrystal;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsOtherMetal;
import mirrg.miragecrops4.api.oregen.ItemsOregen.IEnumGlobs;
import mirrg.miragecrops4.api.oregen.ItemsOregen.IEnumGlobsSlotProvider;
import net.minecraft.block.Block;
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
	public String getModuleName()
	{
		return "oregen";
	}

	//

	@Override
	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLPreInitializationEvent event)
	{

		super.handlePreClient(event);

		MultiIcons.registerMultiIconShapes(getMod().getModId() + ":" + getModuleName() + "/multi/");

	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		ItemsOregen.registerMaterialColors();

		super.handle(event);

		registerWorldgen();

	}

	//

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerBlocks()
	{

		// グロブスロットのインスタンス生成とAPIへの代入
		ItemsOregen.slotOre = new SlotAbstract();
		ItemsOregen.slotBlock = new SlotAbstract();

		((SlotAbstract) ItemsOregen.slotOre).setName("ore");
		((SlotAbstract) ItemsOregen.slotBlock).setName("block");

		// グロブのインスタンス生成とAPIへの代入
		for (IEnumGlobs[] enumGlobs : ItemsOregen.enumGlobsList) {
			createGlob(enumGlobs);
		}

		// ブロックのインスタンス生成と登録とAPIへの代入
		// グロブ・スロットインスタンス生成の後
		ISlot[] slots = {
			ItemsOregen.slotOre,
			ItemsOregen.slotBlock,
		};

		for (IEnumGlobsSlotProvider<?>[] enumGlobs : ItemsOregen.enumGlobsList) {
			IEnumGlobsSlotProvider<?> glob = enumGlobs[0];

			for (ISlot slot : slots) {

				if (glob.isProviding(slot)) {

					String unlocalizedName;
					if (slot == ItemsOregen.slotBlock) {
						unlocalizedName = "block" +
							glob.getCategoryName();
					} else {
						unlocalizedName = "block" +
							HelpersString.toUpperCaseHead(slot.getName()) +
							glob.getCategoryName();
					}

					Exception e = HelpersReflect.setStaticField(ItemsOregen.class, unlocalizedName,
						registerBlock(new BlockMulti(), ItemBlockMultiMirageCrops.class, unlocalizedName));
					if (e != null) throw new RuntimeException(e);

				}

			}

		}

	}

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerItems()
	{

		// アイテムのインスタンス生成と登録とAPIへの代入
		ItemsOregen.itemIngot = registerItem(new ItemMultiIconMirageCrops(), "itemIngot");
		ItemsOregen.itemGem = registerItem(new ItemMultiIconMirageCrops(), "itemGem");
		ItemsOregen.itemDust = registerItem(new ItemMultiIconMirageCrops(), "itemDust");

		// グロブスロットのインスタンス生成とAPIへの代入
		ItemsOregen.slotIngot = new SlotAbstract();
		ItemsOregen.slotGem = new SlotAbstract();
		ItemsOregen.slotDust = new SlotAbstract();

	}

	@Override
	protected void configureBlocks()
	{

		// グロブ・スロットの後
		ISlot[] slots = {
			ItemsOregen.slotOre,
			ItemsOregen.slotBlock,
		};

		for (IEnumGlobsSlotProvider<?>[] enumGlobs : ItemsOregen.enumGlobsList) {
			IEnumGlobsSlotProvider<?> glob = enumGlobs[0];

			for (ISlot slot : slots) {

				if (glob.isProviding(slot)) {

					String unlocalizedName;
					if (slot == ItemsOregen.slotBlock) {
						unlocalizedName = "block" +
							glob.getCategoryName();
					} else {
						unlocalizedName = "block" +
							HelpersString.toUpperCaseHead(slot.getName()) +
							glob.getCategoryName();
					}

					Object obj = HelpersReflect.getStaticField(ItemsOregen.class, unlocalizedName);

					if (obj == null || obj instanceof Exception) {
						throw new RuntimeException((Exception) obj);
					}

					configureBlock((Block) obj, unlocalizedName);

				}

			}

		}

		for (IEnumGlobsSlotProvider<?>[] enumGlobs : ItemsOregen.enumGlobsList) {
			IEnumGlobsSlotProvider<?> glob = enumGlobs[0];

			for (ISlot slot : slots) {

				if (glob.isProviding(slot)) {

					String unlocalizedName;
					if (slot == ItemsOregen.slotBlock) {
						unlocalizedName = "block" +
							glob.getCategoryName();
					} else {
						unlocalizedName = "block" +
							HelpersString.toUpperCaseHead(slot.getName()) +
							glob.getCategoryName();
					}

					Object obj = HelpersReflect.getStaticField(ItemsOregen.class, unlocalizedName);

					if (obj == null || obj instanceof Exception) {
						throw new RuntimeException((Exception) obj);
					}

					createMetaBlock(glob.getValues(), (BlockMulti) obj, slot);

				}

			}

		}

	}

	@Override
	protected void configureItems()
	{

		configureItem(ItemsOregen.itemIngot, "itemIngot");
		configureItem(ItemsOregen.itemGem, "itemGem");
		configureItem(ItemsOregen.itemDust, "itemDust");

		((SlotAbstract) ItemsOregen.slotIngot).setName("ingot");
		((SlotAbstract) ItemsOregen.slotGem).setName("gem");
		((SlotAbstract) ItemsOregen.slotDust).setName("dust");

		// グロブ・スロットの後
		createMetaItem(ItemsOregen.enumGlobsList,
			(ItemMultiIconMirageCrops) ItemsOregen.itemIngot, ItemsOregen.slotIngot, MultiIcons.INGOT);
		createMetaItem(ItemsOregen.enumGlobsList,
			(ItemMultiIconMirageCrops) ItemsOregen.itemGem, ItemsOregen.slotGem, MultiIcons.GEM);
		createMetaItem(ItemsOregen.enumGlobsList,
			(ItemMultiIconMirageCrops) ItemsOregen.itemDust, ItemsOregen.slotDust, MultiIcons.DUST);

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

}
