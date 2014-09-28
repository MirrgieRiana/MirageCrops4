package mirrg.miragecrops4.core;

import static mirrg.miragecrops4.api.oregen.ItemsOregen.*;
import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleCore extends ModuleAbstract
{

	public static CreativeTabs creativeTab;

	public static SimpleNetworkWrapper snw;

	public ModuleCore(IMod mod)
	{
		super(mod);

		snw = NetworkRegistry.INSTANCE.newSimpleChannel(getMod().getModId());

	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{
		creativeTab = new CreativeTabs("miragecrops4") {

			@Override
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem()
			{
				return Item.getItemFromBlock(ItemsOregen.blockOreCalciteGroup);
			}

		};

		registerBlocks();

		String name = "craftingHelmetDataViewer";
		ItemArmor item = new ItemHelmetDataViewer(ItemArmor.ArmorMaterial.IRON, 3, 0);
		item.setUnlocalizedName(name);
		item.setTextureName("iron_helmet");

		GameRegistry.registerItem(item, name);

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

		GameRegistry.addRecipe(new ShapedOreRecipe(blockTorchFeeble,
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.calcite.glob),
			'Y', Blocks.torch));

	}

	@Override
	public String getModuleName()
	{
		return "core";
	}

	public static Block blockTorchFeeble;

	public static Item craftingLeaf;

	protected void registerBlocks()
	{

		{
			String name = "blockTorchFeeble";
			BlockTorchFeeble block = new BlockTorchFeeble();
			block.setHardness(0.0F);
			block.setLightLevel(12);
			block.setStepSound(Block.soundTypeWood);
			block.setBlockName(name);
			block.setBlockTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
			block.setCreativeTab(creativeTab);
			GameRegistry.registerBlock(block, name);
			blockTorchFeeble = block;
		}

		{
			String name = "craftingLeaf";
			Item item = new Item();
			item.setUnlocalizedName(name);
			item.setCreativeTab(CreativeTabs.tabMaterials);
			item.setTextureName(getMod().getModId() + ":" + getModuleName() + "/" + "craftingLeafSarracenia");
			GameRegistry.registerItem(item, name);
			craftingLeaf = item;
		}

	}

}
