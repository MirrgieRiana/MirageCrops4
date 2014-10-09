package mirrg.miragecrops4.crops;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.crop.CropMirage;
import mirrg.mir40.crop.HandlerHarvestOneItem;
import mirrg.mir40.crop.HandlerSpritesBasic;
import mirrg.mir40.crop.HandlerSpritesWrapping;
import mirrg.miragecrops4.core.fairy.crop.CropMirageFairyHousing;
import mirrg.miragecrops4.core.fairy.crop.CropMirageFairyWorkplace;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleCrops extends ModuleAbstract
{

	public ModuleCrops(IMod mod)
	{
		super(mod);
	}

	@Override
	public String getModuleName()
	{
		return "crops";
	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		registerBlocks();

	}

	@Override
	public void handle(FMLInitializationEvent event)
	{

		registerCrops();

		configureCrops();

	}

	//

	public static CropCard cropSarracenia;
	public static CropCard cropLightningSarracenia;
	public static CropCard cropRoseQuartz;

	public static Item craftingLeaf;

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
		// /give ForgeDevName IC2:itemCropSeed 1 0 {id:80,growth:31,gain:0,resistance:0,scan:4}

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

	protected void registerBlocks()
	{

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