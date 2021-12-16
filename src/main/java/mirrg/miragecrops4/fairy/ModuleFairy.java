package mirrg.miragecrops4.fairy;

import static mirrg.miragecrops4.fairy.ModuleFairy.BlocksModuleFairy.*;
import mirrg.h.multi.IMulti;
import mirrg.h.multi.Multi;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.multi.BlockMulti;
import mirrg.mir40.multi.IMetablock;
import mirrg.mir40.net.MessageFieldInt;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobGroup;
import mirrg.mir41.tile.guihandler.GuiHandler;
import mirrg.miragecrops4.api.APICore;
import mirrg.miragecrops4.fairy.glass.HandlerRenderingFairyGlass;
import mirrg.miragecrops4.fairy.glass.ItemFairyGlass;
import mirrg.miragecrops4.fairy.glass.MessageHandlerFairyGlass;
import mirrg.miragecrops4.fairy.machine.TileEntityMirageMachineChest;
import mirrg.miragecrops4.fairy.multi.BlockMultiMirageMachine;
import mirrg.miragecrops4.fairy.multi.ItemBlockMultiMirageMachine;
import mirrg.miragecrops4.fairy.multi.MetablockMirageMachine;
import mirrg.miragecrops4.fairy.multi.RenderBlockMirageMachine;
import mirrg.miragecrops4.fairy.multi.TileEntityMirageMachine;
import mirrg.miragecrops4.lib.ModuleMirageCropsBase;
import mirrg.miragecrops4.lib.oregen.GlobsOregen;
import mirrg.miragecrops4.lib.oregen.HelpersOregen;
import mirrg.miragecrops4.lib.oregen.ItemsOregen;
import mirrg.miragecrops4.lib.oregen.multi.MetablockOregen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SuppressWarnings({
	"rawtypes", "unchecked"
})
public class ModuleFairy extends ModuleMirageCropsBase
{

	public ModuleFairy(IMod mod)
	{
		super(mod);

	}

	@Override
	public String getModuleName()
	{
		return "fairy";
	}

	//

	public static SimpleNetworkWrapper snw;

	public static GuiHandler guiHandler;

	@SideOnly(Side.CLIENT)
	public static RenderBlockMirageMachine renderBlockMirageMachine;

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		snw = NetworkRegistry.INSTANCE.newSimpleChannel(getMod().getModId());

		super.handle(event);

		GameRegistry.registerTileEntity(TileEntityMirageMachine.class, "MirageMachine");
		GameRegistry.registerTileEntity(TileEntityMirageMachineChest.class, "MirageMachineChest");

	}

	@Override
	public void handle(FMLInitializationEvent event)
	{

		snw.registerMessage(MessageHandlerFairyGlass.class, MessageFieldInt.class, 0, Side.CLIENT);

		super.handle(event);

		{
			MetablockOregen metablock = new MetablockOreSpinatite(GlobsOregen.EnumGlob.spinatite.glob,
				GlobsOregen.EnumSlot.ore.slot);

			HelpersOregen.overrideMetablock(
				GlobsOregen.EnumSlot.ore, GlobsOregen.EnumGlobGroup.MirageMagic,
				GlobsOregen.EnumGlob.spinatite, metablock);
			HelpersOregen.configureMetablock(
				ItemsOregen.moduleOregen, metablock, gdn(GlobsOregen.EnumSlot.ore, GlobsOregen.EnumGlob.spinatite));
		}

		{
			guiHandler = new GuiHandler(1, getMod());
			NetworkRegistry.INSTANCE.registerGuiHandler(getMod(), guiHandler);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClient(FMLInitializationEvent event)
	{

		{
			int renderId = RenderingRegistry.getNextAvailableRenderId();
			renderBlockMirageMachine = new RenderBlockMirageMachine(renderId);
			RenderingRegistry.registerBlockHandler(renderId, renderBlockMirageMachine);
		}

		MinecraftForge.EVENT_BUS.register(new HandlerRenderingFairyGlass());

		super.handleClient(event);

	}

	//

	public static ItemFairyGlass craftingFairyGlass;
	public static ItemTool craftingToolHardHammerSpinachium;
	public static ItemDustMirage dustMirage;
	public static Item craftingToolMirageFairy;

	public static class BlocksModuleFairy
	{
		public static BlockMultiMirageMachine tier10;
		public static BlockMultiMirageMachine tier11;
		public static BlockMultiMirageMachine tier12;
		public static BlockMultiMirageMachine tier13;
		public static BlockMultiMirageMachine tier14;
	}

	@Override
	protected void registerBlocks()
	{
		String p = "blockMirageMachine"; // prefix
		Class<ItemBlockMultiMirageMachine> cib = ItemBlockMultiMirageMachine.class;

		tier10 = registerBlock(createBlock(), cib, p + "Tier10");
		tier11 = registerBlock(createBlock(), cib, p + "Tier11");
		tier12 = registerBlock(createBlock(), cib, p + "Tier12");
		tier13 = registerBlock(createBlock(), cib, p + "Tier13");
		tier14 = registerBlock(createBlock(), cib, p + "Tier14");
	}

	private BlockMultiMirageMachine createBlock()
	{
		return new BlockMultiMirageMachine(Material.piston, new Multi(new MetablockMirageMachine[256]));
	}

	@Override
	protected void registerItems()
	{
		craftingFairyGlass = registerItem(new ItemFairyGlass(), "craftingFairyGlass");
		craftingToolHardHammerSpinachium = registerItem(
			new ItemToolCrafting(), "craftingToolHardHammerSpinachium");
		dustMirage = registerItem(new ItemDustMirage(), "dustMirage");
		craftingToolMirageFairy = registerItem(new Item(), "craftingToolMirageFairy");
	}

	@Override
	protected void configureBlocks()
	{
		String p = "blockMirageMachine"; // prefix

		configureBlock(tier10, p + "Tier10", 0.5f, 0, Block.soundTypePiston);
		tier10.setCreativeTab(APICore.creativeTab);
		configureBlock(tier11, p + "Tier11", 0.5f, 0, Block.soundTypePiston);
		tier11.setCreativeTab(APICore.creativeTab);
		configureBlock(tier12, p + "Tier12", 0.5f, 0, Block.soundTypePiston);
		tier12.setCreativeTab(APICore.creativeTab);
		configureBlock(tier13, p + "Tier13", 0.5f, 0, Block.soundTypePiston);
		tier13.setCreativeTab(APICore.creativeTab);
		configureBlock(tier14, p + "Tier14", 0.5f, 0, Block.soundTypePiston);
		tier14.setCreativeTab(APICore.creativeTab);

		bmb(tier10, 0, createMetablock(null), p + "Tier10");
		bmb(tier11, 0, createMetablock(null), p + "Tier11");
		bmb(tier12, 0, createMetablock(null), p + "Tier12");
		bmb(tier13, 0, createMetablock(null), p + "Tier13");
		bmb(tier14, 0, createMetablock(null), p + "Tier14");

		bmb(tier10, 16, createMetablock(TileEntityMirageMachineChest.class), p + "ChestTier10");
		bmb(tier11, 16, createMetablock(TileEntityMirageMachineChest.class), p + "ChestTier11");
		bmb(tier12, 16, createMetablock(TileEntityMirageMachineChest.class), p + "ChestTier12");
		bmb(tier13, 16, createMetablock(TileEntityMirageMachineChest.class), p + "ChestTier13");
		bmb(tier14, 16, createMetablock(TileEntityMirageMachineChest.class), p + "ChestTier14");
	}

	private MetablockMirageMachine createMetablock(Class<?> classTileEntity)
	{
		MetablockMirageMachine metablock = new MetablockMirageMachine();
		if (classTileEntity != null) metablock.classTileEntity = classTileEntity;
		return metablock;
	}

	// bindMetablock
	private <MULTI extends IMulti<MULTI, META>, META extends IMetablock<MULTI, META>> void bmb(
		BlockMulti<MULTI, META> blockMulti, int id, META meta, String unlocalizedName)
	{
		meta.setUnlocalizedName(unlocalizedName);
		if (getMod().isClient()) {
			meta.setIconName(getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName);
		}
		blockMulti.getMulti().bind(id, meta);
	}

	@Override
	protected void configureItems()
	{

		{
			String name = "craftingFairyGlass";
			configureItem(craftingFairyGlass, name);
			craftingFairyGlass.setCreativeTab(APICore.creativeTab);
			craftingFairyGlass.armorTexture1 =
				getMod().getModId() + ":textures/models/armor/" + getModuleName() + "/" + name + "_1.png";
			craftingFairyGlass.armorTexture2 =
				getMod().getModId() + ":textures/models/armor/" + getModuleName() + "/" + name + "_2.png";
		}

		configureItem(craftingToolHardHammerSpinachium, "craftingToolHardHammerSpinachium");
		craftingToolHardHammerSpinachium.setCreativeTab(APICore.creativeTab);
		craftingToolHardHammerSpinachium.setMaxDamage(32 - 1);
		OreDictionary.registerOre("craftingToolHardHammer",
			new ItemStack(craftingToolHardHammerSpinachium, 1, 32767));

		configureItem(dustMirage, "dustMirage");
		dustMirage.setCreativeTab(APICore.creativeTab);

		configureItem(craftingToolMirageFairy, "craftingToolMirageFairy");
		craftingToolMirageFairy.setCreativeTab(APICore.creativeTab);
		OreDictionary.registerOre("craftingToolMirageFairy",
			new ItemStack(craftingToolMirageFairy));

	}

	private void addRecipeMineral(GlobGroup<Glob> globGroup, Glob glob)
	{

		if (globGroup.allowsSlot(GlobsOregen.EnumSlot.gem.slot)) {

			// 鉱石→結晶
			GameRegistry.addRecipe(new ShapelessOreRecipe(
				cpy(GlobsOregen.EnumSlot.gem.slot, glob),
				gdn(GlobsOregen.EnumSlot.ore.slot, glob),
				"craftingToolHardHammer"));

			// 結晶→粉末
			GameRegistry.addRecipe(new ShapelessOreRecipe(
				cpy(GlobsOregen.EnumSlot.dust.slot, glob),
				gdn(GlobsOregen.EnumSlot.gem.slot, glob),
				"craftingToolHardHammer"));

		} else {

			// 鉱石→粉末
			GameRegistry.addRecipe(new ShapelessOreRecipe(
				cpy(GlobsOregen.EnumSlot.dust.slot, glob),
				gdn(GlobsOregen.EnumSlot.ore.slot, glob),
				"craftingToolHardHammer"));

		}

		if (globGroup.allowsSlot(GlobsOregen.EnumSlot.block.slot)) {

			// 結晶*9→ブロック
			GameRegistry.addRecipe(new ShapedOreRecipe(
				cpy(GlobsOregen.EnumSlot.block.slot, glob),
				"XXX",
				"XXX",
				"XXX",
				'X', gdn(GlobsOregen.EnumSlot.gem.slot, glob)));

			// 結晶*9←ブロック
			GameRegistry.addRecipe(new ShapedOreRecipe(
				cpy(GlobsOregen.EnumSlot.gem.slot, glob, 9),
				"X",
				'X', gdn(GlobsOregen.EnumSlot.block.slot, glob)));

		}

	}

	@Override
	protected void registerRecipes()
	{

		GameRegistry.addRecipe(new ShapedOreRecipe(craftingFairyGlass,
			"III",
			"IGI",
			'I', gdn(GlobsOregen.EnumSlot.ingot, GlobsOregen.EnumGlob.spinachium),
			'G', gdn(GlobsOregen.EnumSlot.gem, GlobsOregen.EnumGlob.calcite)));

		// 124578スピナチウム+6棒→スピナチウムのハンマー
		GameRegistry.addRecipe(new ShapedOreRecipe(craftingToolHardHammerSpinachium,
			"II ",
			"IIS",
			"II ",
			'I', gdn(GlobsOregen.EnumSlot.ingot, GlobsOregen.EnumGlob.spinachium),
			'S', "stickWood"));

		// 鉱物 鉱石→結晶→粉末
		for (Glob glob : GlobsOregen.EnumGlobGroup.CalciteGroup.globGroup) {
			addRecipeMineral(GlobsOregen.EnumGlobGroup.CalciteGroup.globGroup, glob);
		}
		for (Glob glob : GlobsOregen.EnumGlobGroup.MohsHardnessCrystal.globGroup) {
			addRecipeMineral(GlobsOregen.EnumGlobGroup.MohsHardnessCrystal.globGroup, glob);
		}
		for (Glob glob : GlobsOregen.EnumGlobGroup.MirageMagic.globGroup) {
			addRecipeMineral(GlobsOregen.EnumGlobGroup.MirageMagic.globGroup, glob);
		}

		// スピナタイト鉱石→スピナチウムインゴット
		// スピナタイト結晶→スピナチウムインゴット
		// スピナタイト粉→スピナチウム粉
		// スピナチウム粉→スピナチウムインゴット
		GameRegistry.addSmelting(
			cpy(GlobsOregen.EnumSlot.ore, GlobsOregen.EnumGlob.spinatite),
			cpy(GlobsOregen.EnumSlot.ingot, GlobsOregen.EnumGlob.spinachium), 1);
		GameRegistry.addSmelting(
			cpy(GlobsOregen.EnumSlot.gem, GlobsOregen.EnumGlob.spinatite),
			cpy(GlobsOregen.EnumSlot.ingot, GlobsOregen.EnumGlob.spinachium), 1);
		GameRegistry.addSmelting(
			cpy(GlobsOregen.EnumSlot.dust, GlobsOregen.EnumGlob.spinatite),
			cpy(GlobsOregen.EnumSlot.dust, GlobsOregen.EnumGlob.spinachium), 1);
		GameRegistry.addSmelting(
			cpy(GlobsOregen.EnumSlot.dust, GlobsOregen.EnumGlob.spinachium),
			cpy(GlobsOregen.EnumSlot.ingot, GlobsOregen.EnumGlob.spinachium), 1);

		// カルサイト粉+スピナチウム粉→ミラージュパウダー
		GameRegistry.addRecipe(new ShapelessOreRecipe(
			new ItemStack(dustMirage, 4),
			gdn(GlobsOregen.EnumSlot.dust, GlobsOregen.EnumGlob.calcite),
			gdn(GlobsOregen.EnumSlot.dust, GlobsOregen.EnumGlob.calcite),
			gdn(GlobsOregen.EnumSlot.dust, GlobsOregen.EnumGlob.calcite),
			gdn(GlobsOregen.EnumSlot.dust, GlobsOregen.EnumGlob.spinachium)));
		;

	}
}
