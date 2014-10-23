package mirrg.miragecrops4.fairy;

import mirrg.mir34.modding.IMod;
import mirrg.mir40.net.MessageFieldInt;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobGroup;
import mirrg.miragecrops4.core.ModuleCore;
import mirrg.miragecrops4.core.ModuleMirageCropsBase;
import mirrg.miragecrops4.fairy.glass.HandlerRenderingFairyGlass;
import mirrg.miragecrops4.fairy.glass.ItemFairyGlass;
import mirrg.miragecrops4.fairy.glass.MessageHandlerFairyGlass;
import mirrg.miragecrops4.oregen.ModuleOregenBase;
import mirrg.miragecrops4.oregen.global.ItemsOregen;
import mirrg.miragecrops4.oregen.global.ItemsOregen.EnumGlobGroup;
import mirrg.miragecrops4.oregen.global.ItemsOregen.EnumGlob;
import mirrg.miragecrops4.oregen.global.ItemsOregen.EnumSlot;
import mirrg.miragecrops4.oregen.multi.MetablockGlob;
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
			MetablockGlob.Raw metablock = new MetablockOreSpinatite(EnumGlob.spinatite.glob, EnumSlot.ore.slot);

			ModuleOregenBase.overrideMetablock(
				EnumSlot.ore.slot, EnumGlobGroup.MirageMagic.globGroup, EnumGlob.spinatite.glob, metablock);
			ModuleOregenBase.configureMetablock(
				ItemsOregen.moduleOregen, metablock, gdn(EnumSlot.ore, EnumGlob.spinatite));
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
	protected void configureItems()
	{

		{
			String name = "craftingFairyGlass";
			configureItem(craftingFairyGlass, name);
			craftingFairyGlass.setCreativeTab(ModuleCore.creativeTab);
			craftingFairyGlass.armorTexture1 =
				getMod().getModId() + ":textures/models/armor/" + getModuleName() + "/" + name + "_1.png";
			craftingFairyGlass.armorTexture2 =
				getMod().getModId() + ":textures/models/armor/" + getModuleName() + "/" + name + "_2.png";
		}

		configureItem(craftingToolHardHammerSpinachium, "craftingToolHardHammerSpinachium");
		craftingToolHardHammerSpinachium.setCreativeTab(ModuleCore.creativeTab);
		craftingToolHardHammerSpinachium.setMaxDamage(32 - 1);
		OreDictionary.registerOre("craftingToolHardHammer",
			new ItemStack(craftingToolHardHammerSpinachium, 1, 32767));

		configureItem(dustMirage, "dustMirage");
		dustMirage.setCreativeTab(ModuleCore.creativeTab);

		configureItem(craftingToolMirageFairy, "craftingToolMirageFairy");
		craftingToolMirageFairy.setCreativeTab(ModuleCore.creativeTab);
		OreDictionary.registerOre("craftingToolMirageFairy",
			new ItemStack(craftingToolMirageFairy));

	}

	private void addRecipeMineral(GlobGroup<Glob> globGroup, Glob glob)
	{

		if (globGroup.allowsSlot(EnumSlot.gem.slot)) {

			// 鉱石→結晶
			GameRegistry.addRecipe(new ShapelessOreRecipe(
				cpy(EnumSlot.gem.slot, glob),
				gdn(EnumSlot.ore.slot, glob),
				"craftingToolHardHammer"));

			// 結晶→粉末
			GameRegistry.addRecipe(new ShapelessOreRecipe(
				cpy(EnumSlot.dust.slot, glob),
				gdn(EnumSlot.gem.slot, glob),
				"craftingToolHardHammer"));

		} else {

			// 鉱石→粉末
			GameRegistry.addRecipe(new ShapelessOreRecipe(
				cpy(EnumSlot.dust.slot, glob),
				gdn(EnumSlot.ore.slot, glob),
				"craftingToolHardHammer"));

		}

		if (globGroup.allowsSlot(EnumSlot.block.slot)) {

			// 結晶*9→ブロック
			GameRegistry.addRecipe(new ShapedOreRecipe(
				cpy(EnumSlot.block.slot, glob),
				"XXX",
				"XXX",
				"XXX",
				'X', gdn(EnumSlot.gem.slot, glob)));

			// 結晶*9←ブロック
			GameRegistry.addRecipe(new ShapedOreRecipe(
				cpy(EnumSlot.gem.slot, glob, 9),
				"X",
				'X', gdn(EnumSlot.block.slot, glob)));

		}

	}

	@Override
	protected void registerRecipes()
	{

		GameRegistry.addRecipe(new ShapedOreRecipe(craftingFairyGlass,
			"III",
			"IGI",
			'I', gdn(EnumSlot.ingot, EnumGlob.spinachium),
			'G', gdn(EnumSlot.gem, EnumGlob.calcite)));

		// 124578スピナチウム+6棒→スピナチウムのハンマー
		GameRegistry.addRecipe(new ShapedOreRecipe(craftingToolHardHammerSpinachium,
			"II ",
			"IIS",
			"II ",
			'I', gdn(EnumSlot.ingot, EnumGlob.spinachium),
			'S', "stickWood"));

		// 鉱物 鉱石→結晶→粉末
		for (Glob glob : EnumGlobGroup.CalciteGroup.globGroup.getGlobs()) {
			addRecipeMineral(EnumGlobGroup.CalciteGroup.globGroup, glob);
		}
		for (Glob glob : EnumGlobGroup.MohsHardnessCrystal.globGroup.getGlobs()) {
			addRecipeMineral(EnumGlobGroup.MohsHardnessCrystal.globGroup, glob);
		}
		for (Glob glob : EnumGlobGroup.MirageMagic.globGroup.getGlobs()) {
			addRecipeMineral(EnumGlobGroup.MirageMagic.globGroup, glob);
		}

		// スピナタイト鉱石→スピナチウムインゴット
		// スピナタイト結晶→スピナチウムインゴット
		// スピナタイト粉→スピナチウム粉
		// スピナチウム粉→スピナチウムインゴット
		GameRegistry.addSmelting(
			cpy(EnumSlot.ore, EnumGlob.spinatite),
			cpy(EnumSlot.ingot, EnumGlob.spinachium), 1);
		GameRegistry.addSmelting(
			cpy(EnumSlot.gem, EnumGlob.spinatite),
			cpy(EnumSlot.ingot, EnumGlob.spinachium), 1);
		GameRegistry.addSmelting(
			cpy(EnumSlot.dust, EnumGlob.spinatite),
			cpy(EnumSlot.dust, EnumGlob.spinachium), 1);
		GameRegistry.addSmelting(
			cpy(EnumSlot.dust, EnumGlob.spinachium),
			cpy(EnumSlot.ingot, EnumGlob.spinachium), 1);

		// カルサイト粉+スピナチウム粉→ミラージュパウダー
		GameRegistry.addRecipe(new ShapelessOreRecipe(
			new ItemStack(dustMirage, 4),
			gdn(EnumSlot.dust, EnumGlob.calcite),
			gdn(EnumSlot.dust, EnumGlob.calcite),
			gdn(EnumSlot.dust, EnumGlob.calcite),
			gdn(EnumSlot.dust, EnumGlob.spinachium)));
		;

	}
}
