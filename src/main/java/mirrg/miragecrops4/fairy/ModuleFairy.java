package mirrg.miragecrops4.fairy;

import static mirrg.miragecrops4.api.oregen.ItemsOregen.*;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.mir40.net.MessageFieldInt;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMirageMagic;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMirageMaterial;
import mirrg.miragecrops4.core.ModuleCore;
import mirrg.miragecrops4.core.ModuleMirageCropsBase;
import mirrg.miragecrops4.fairy.glass.HandlerRenderingFairyGlass;
import mirrg.miragecrops4.fairy.glass.ItemFairyGlass;
import mirrg.miragecrops4.fairy.glass.MessageHandlerFairyGlass;
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

	@Override
	protected void registerItems()
	{
		craftingFairyGlass = registerItem(new ItemFairyGlass(), "craftingFairyGlass");
		craftingToolHardHammerSpinachium = registerItem(
			new ItemToolCrafting(), "craftingToolHardHammerSpinachium");
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

	}

	@Override
	protected void registerRecipes()
	{

		GameRegistry.addRecipe(new ShapedOreRecipe(craftingFairyGlass,
			"III",
			"IGI",
			'I', HelpersGlob.getDictionaryName(slotIngot, EnumGlobsMirageMaterial.spinachium.glob),
			'G', HelpersGlob.getDictionaryName(slotGem, EnumGlobsCalciteGroup.calcite.glob)));

		GameRegistry.addRecipe(new ShapedOreRecipe(craftingToolHardHammerSpinachium,
			"II ",
			"IIS",
			"II ",
			'I', HelpersGlob.getDictionaryName(slotIngot, EnumGlobsMirageMaterial.spinachium.glob),
			'S', "stickWood"));

		GameRegistry.addRecipe(new ShapelessOreRecipe(
			EnumGlobsCalciteGroup.calcite.glob.copy(slotGem),
			HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.calcite.glob),
			"craftingToolHardHammer"));

		GameRegistry.addSmelting(
			EnumGlobsMirageMagic.spinatite.glob.copy(slotOre),
			EnumGlobsMirageMaterial.spinachium.glob.copy(slotIngot), 1);

	}

}
