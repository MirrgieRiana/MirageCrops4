package mirrg.miragecrops4.core;

import static mirrg.miragecrops4.api.oregen.ItemsOregen.*;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import mirrg.miragecrops4.core.crop.CropMirage;
import mirrg.miragecrops4.core.crop.HandlerHarvestOneItem;
import mirrg.miragecrops4.core.crop.HandlerSpritesBasic;
import mirrg.miragecrops4.core.crop.HandlerSpritesWrapping;
import mirrg.miragecrops4.core.fairy.crop.CropMirageFairyHousing;
import mirrg.miragecrops4.core.fairy.crop.CropMirageFairyWorkplace;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
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

	public static CropCard cropSarracenia;
	public static CropCard cropLightningSarracenia;
	public static CropCard cropRoseQuartz;

	protected <T extends CropCard> T registerCrop(int id, T crop)
	{
		Crops.instance.registerCrop(crop, id);
		return crop;
	}

	protected void registerCrops()
	{
		cropSarracenia = registerCrop(80, new CropMirage());
		cropLightningSarracenia = registerCrop(81, new CropMirageFairyHousing());
		cropRoseQuartz = registerCrop(100, new CropMirageFairyWorkplace());
	}

	protected void configureCrops()
	{
		String maskMiragecrops4 = "miragecrops4:crop/blockCrop.%name%.%size%";

		CropMirage crop;

		crop = (CropMirage) cropSarracenia;
		crop.setStatusRegular("Sarracenia", 3, "Mirrgie Riana");
		crop.setStatusCrossing(0, 0, 3, 1, 2, "Sarracenia");
		crop.setMaxSize(5);
		crop.setHandlerSprites(
			new HandlerSpritesBasic(crop, maskMiragecrops4));
		crop.setHandlerHarvest(
			new HandlerHarvestOneItem(crop, new ItemStack(craftingLeaf)));

		crop = (CropMirage) cropLightningSarracenia;
		crop.setStatusRegular("Lightning Sarracenia", 5, "Mirrgie Riana");
		crop.setStatusCrossing(1, 0, 3, 2, 2, "Sarracenia", "Lightning", "Purple");
		crop.setMaxSize(5);
		crop.setHandlerSprites(
			new HandlerSpritesWrapping(crop, maskMiragecrops4, cropSarracenia, 5, 5));
		crop.setHandlerHarvest(
			new HandlerHarvestOneItem(crop, new ItemStack(craftingLeaf)));

		crop = (CropMirage) cropRoseQuartz;
		crop.setStatusRegular("Rose Quartz", 7, "Mirrgie Riana");
		crop.setStatusCrossing(2, 0, 1, 3, 0, "Roze");
		crop.setMaxSize(4);
		crop.setHandlerSprites(
			new HandlerSpritesBasic(crop, maskMiragecrops4));
		crop.setHandlerHarvest(
			new HandlerHarvestOneItem(crop, new ItemStack(craftingLeaf)));

	}

	@Override
	public void handle(FMLInitializationEvent event)
	{

		snw.registerMessage(MessageHandler.class, MessageDataViewInt.class, 0, Side.CLIENT);

		// /give ForgeDevName IC2:itemCropSeed 1 0 {id:80,growth:31,gain:0,resistance:0,scan:4}

		registerCrops();

		configureCrops();

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
