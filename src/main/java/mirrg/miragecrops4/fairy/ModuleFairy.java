package mirrg.miragecrops4.fairy;

import mirrg.h.multi.IMulti;
import mirrg.h.multi.Multi;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.multi.BlockMulti;
import mirrg.mir40.multi.IMetablock;
import mirrg.mir40.multi.ItemBlockMulti;
import mirrg.mir40.multi.Metablock;
import mirrg.mir40.net.MessageFieldInt;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobGroup;
import mirrg.miragecrops4.api.APICore;
import mirrg.miragecrops4.fairy.glass.HandlerRenderingFairyGlass;
import mirrg.miragecrops4.fairy.glass.ItemFairyGlass;
import mirrg.miragecrops4.fairy.glass.MessageHandlerFairyGlass;
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

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		snw = NetworkRegistry.INSTANCE.newSimpleChannel(getMod().getModId());

		super.handle(event);

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

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClient(FMLInitializationEvent event)
	{

		MinecraftForge.EVENT_BUS.register(new HandlerRenderingFairyGlass());

		super.handleClient(event);

	}

	//

	public static ItemFairyGlass craftingFairyGlass;
	public static ItemTool craftingToolHardHammerSpinachium;
	public static ItemDustMirage dustMirage;
	public static Item craftingToolMirageFairy;

	public static BlockMulti blockMirageMachine;

	@Override
	protected void registerBlocks()
	{
		blockMirageMachine = registerBlock(
			new BlockMulti(Material.piston, new Multi(new Metablock[16])),
			ItemBlockMulti.class, "blockMirageMachine");
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
		configureBlock(blockMirageMachine, "blockMirageMachine", 0.5f, 0, Block.soundTypePiston);
		blockMirageMachine.setCreativeTab(APICore.creativeTab);

		bindMetablock(blockMirageMachine, 0, new Metablock(), "blockMirageMachineVeryLow");
		bindMetablock(blockMirageMachine, 1, new Metablock(), "blockMirageMachineLow");
		bindMetablock(blockMirageMachine, 2, new Metablock(), "blockMirageMachineMiddle");
		bindMetablock(blockMirageMachine, 3, new Metablock(), "blockMirageMachineHigh");
		bindMetablock(blockMirageMachine, 4, new Metablock(), "blockMirageMachineVeryHigh");

	}

	private <MULTI extends IMulti<MULTI, META>, META extends IMetablock<MULTI, META>> void bindMetablock(
		BlockMulti<MULTI, META> blockMulti, int id, META meta, String unlocalizedName)
	{
		meta.setUnlocalizedName(unlocalizedName);
		if (getMod().isClient()) {
			meta.setIconName(getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName);
		}
		blockMulti.multi.bind(id, meta);
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
		for (Glob glob : GlobsOregen.EnumGlobGroup.CalciteGroup.globGroup.getGlobs()) {
			addRecipeMineral(GlobsOregen.EnumGlobGroup.CalciteGroup.globGroup, glob);
		}
		for (Glob glob : GlobsOregen.EnumGlobGroup.MohsHardnessCrystal.globGroup.getGlobs()) {
			addRecipeMineral(GlobsOregen.EnumGlobGroup.MohsHardnessCrystal.globGroup, glob);
		}
		for (Glob glob : GlobsOregen.EnumGlobGroup.MirageMagic.globGroup.getGlobs()) {
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
