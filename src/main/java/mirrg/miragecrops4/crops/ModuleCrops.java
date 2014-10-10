package mirrg.miragecrops4.crops;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.crop.CropMirage;
import mirrg.mir40.crop.HandlerHarvestOneItem;
import mirrg.mir40.crop.HandlerSpritesBasic;
import mirrg.mir40.crop.HandlerSpritesWrapping;
import mirrg.miragecrops4.core.ModuleCore;
import mirrg.miragecrops4.core.ModuleMirageCropsBase;
import mirrg.miragecrops4.crops.fairy.CropMirageFairyHousing;
import mirrg.miragecrops4.crops.fairy.CropMirageFairyWorkplace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ModuleCrops extends ModuleMirageCropsBase
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

	//

	@Override
	public void handle(FMLInitializationEvent event)
	{

		super.handle(event);

		registerCrops();

		configureCrops();

	}

	protected <T extends CropMirage> T registerCrop(int id, T crop, String name)
	{
		crop.setName(name);
		Crops.instance.registerCrop(crop, id);
		return crop;
	}

	//

	public static Item craftingLeaf;

	public static CropCard cropSarracenia;
	public static CropCard cropLightningSarracenia;
	public static CropCard cropRoseQuartz;

	@Override
	protected void registerItems()
	{

		craftingLeaf = registerItem(new Item(), "craftingLeaf");

	}

	@Override
	protected void configureItems()
	{

		configureItem(craftingLeaf, "craftingLeaf");
		craftingLeaf.setCreativeTab(ModuleCore.creativeTab);
		craftingLeaf.setTextureName(getMod().getModId() + ":" + getModuleName() + "/" + "craftingLeafSarracenia");

	}

	protected void registerCrops()
	{
		cropSarracenia = registerCrop(80, new CropMirageCropsBase(), "Sarracenia");
		cropLightningSarracenia = registerCrop(81, new CropMirageFairyHousing(), "Lightning Sarracenia");
		cropRoseQuartz = registerCrop(100, new CropMirageFairyWorkplace(), "Rose Quartz");
	}

	protected void configureCrops()
	{
		// /give ForgeDevName IC2:itemCropSeed 1 0 {id:80,growth:31,gain:0,resistance:0,scan:4}

		String maskMiragecrops4 = "miragecrops4:crop/blockCrop.%name%.%size%";

		CropMirageCropsBase crop;

		crop = (CropMirageCropsBase) cropSarracenia;
		crop.setStatusRegular(3, "Mirrgie Riana");
		crop.setStatusCrossing(0, 0, 3, 1, 2, "Sarracenia");
		crop.setMaxSize(5);
		crop.setHandlerSprites(
			new HandlerSpritesBasic(crop, maskMiragecrops4));
		crop.setHandlerHarvest(
			new HandlerHarvestOneItem(crop, new ItemStack(craftingLeaf)));

		crop = (CropMirageCropsBase) cropLightningSarracenia;
		crop.setStatusRegular(5, "Mirrgie Riana");
		crop.setStatusCrossing(1, 0, 3, 2, 2, "Sarracenia", "Lightning", "Purple");
		crop.setMaxSize(5);
		crop.setHandlerSprites(
			new HandlerSpritesWrapping(crop, maskMiragecrops4, cropSarracenia, 5, 5));
		crop.setHandlerHarvest(
			new HandlerHarvestOneItem(crop, new ItemStack(craftingLeaf)));

		crop = (CropMirageCropsBase) cropRoseQuartz;
		crop.setStatusRegular(7, "Mirrgie Riana");
		crop.setStatusCrossing(2, 0, 1, 3, 0, "Roze");
		crop.setMaxSize(4);
		crop.setHandlerSprites(
			new HandlerSpritesBasic(crop, maskMiragecrops4));
		crop.setHandlerHarvest(
			new HandlerHarvestOneItem(crop, new ItemStack(craftingLeaf)));

	}

}
