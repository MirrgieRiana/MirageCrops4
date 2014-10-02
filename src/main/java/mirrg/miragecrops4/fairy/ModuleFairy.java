package mirrg.miragecrops4.fairy;

import static mirrg.miragecrops4.api.oregen.ItemsOregen.*;

import java.util.HashSet;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMirageMagic;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsMirageMaterial;
import mirrg.miragecrops4.core.ModuleCore;
import mirrg.miragecrops4.fairy.glass.HandlerRendering;
import mirrg.miragecrops4.fairy.glass.ItemFairyGlass;
import mirrg.miragecrops4.fairy.glass.MessageDataViewInt;
import mirrg.miragecrops4.fairy.glass.MessageHandler;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleFairy extends ModuleAbstract
{

	public static SimpleNetworkWrapper snw;

	public ModuleFairy(IMod mod)
	{
		super(mod);

		snw = NetworkRegistry.INSTANCE.newSimpleChannel(getMod().getModId());

	}

	@Override
	public String getModuleName()
	{
		return "fairy";
	}

	public static ItemArmor craftingHelmetDataViewer;

	public static ItemTool craftingToolHardHammerSpinachium;

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		{
			String name = "craftingHelmetDataViewer";
			ItemArmor item = new ItemFairyGlass();

			item.setUnlocalizedName(name);
			item.setTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
			item.setCreativeTab(ModuleCore.creativeTab);
			GameRegistry.registerItem(item, name);

			craftingHelmetDataViewer = item;
		}

		{
			String name = "craftingToolHardHammerSpinachium";
			ItemTool item = new ItemTool(1, ToolMaterial.IRON, new HashSet()) {

			};

			item.setUnlocalizedName(name);
			item.setTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
			item.setCreativeTab(ModuleCore.creativeTab);
			GameRegistry.registerItem(item, name);

			OreDictionary.registerOre("craftingToolHardHammer", item);
			craftingToolHardHammerSpinachium = item;
		}

	}

	@Override
	public void handle(FMLInitializationEvent event)
	{

		snw.registerMessage(MessageHandler.class, MessageDataViewInt.class, 0, Side.CLIENT);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClient(FMLInitializationEvent event)
	{

		MinecraftForge.EVENT_BUS.register(new HandlerRendering());

	}

	@Override
	public void handle(FMLPostInitializationEvent event)
	{

		GameRegistry.addRecipe(new ShapedOreRecipe(craftingHelmetDataViewer,
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
