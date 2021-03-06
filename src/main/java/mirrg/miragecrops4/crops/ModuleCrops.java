package mirrg.miragecrops4.crops;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import mirrg.h.multi.IMeta;
import mirrg.h.multi.Multi;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.crop.CropMirage;
import mirrg.mir40.crop.HandlerHarvestOneItem;
import mirrg.mir40.crop.HandlerSpritesBasic;
import mirrg.mir40.icon.HelpersIcon;
import mirrg.mir40.multi.ItemMulti;
import mirrg.mir40.multi.Metaitem;
import mirrg.miragecrops4.api.APICore;
import mirrg.miragecrops4.lib.CropMirageCropsBase;
import mirrg.miragecrops4.lib.ModuleMirageCropsBase;
import mirrg.miragecrops4.lib.fairycrop.CropMirageFairyHousing;
import mirrg.miragecrops4.lib.fairycrop.CropMirageFairyWorkplace;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@SuppressWarnings({
	"rawtypes", "unchecked"
})
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

	public static ItemMulti craftingLeaf;

	public static CropCard cropSarracenia;
	public static CropCard cropSpinach;

	@Override
	protected void registerItems()
	{

		craftingLeaf = registerItem(new ItemMulti(new Multi(new IMeta[10])), "craftingLeaf");

	}

	@Override
	protected void configureItems()
	{

		configureItem(craftingLeaf, "craftingLeaf");
		craftingLeaf.setCreativeTab(APICore.creativeTab);

		craftingLeaf.setTextureName(getMod().getModId() + ":" + getModuleName() + "/" + "craftingLeafSarracenia");

		configureCraftingLeaf(craftingLeaf);

	}

	protected void configureCraftingLeaf(ItemMulti item)
	{

		{
			int index = 0;
			Metaitem metaitem = new Metaitem();
			String unlocalizedName = "craftingLeafSarracenia";

			item.multi.bind(index, metaitem);

			metaitem.unlocalizedName = unlocalizedName;
			if (getMod().isClient()) {
				String textureName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
				if (HelpersIcon.getURLFromTextureName(textureName, HelpersIcon.TYPE_ITEMS) != null) {
					metaitem.iconName = textureName;
				}
				metaitem.iconName = getMod().getModId() + ":" + getModuleName() + "/" + unlocalizedName;
			}

			OreDictionary.registerOre(unlocalizedName, new ItemStack(craftingLeaf, 1, index));
		}

	}

	protected void registerCrops()
	{
		cropSarracenia = registerCrop(80, new CropMirageFairyHousing(), "Sarracenia");
		cropSpinach = registerCrop(81, new CropMirageFairyWorkplace(), "Spinach");
	}

	protected void configureCrops()
	{
		// /give ForgeDevName IC2:itemCropSeed 1 0 {id:80,growth:31,gain:0,resistance:0,scan:4}

		String maskMiragecrops4 = getMod().getModId() + ":" + getModuleName() + "/blockCrop.%name%.%size%";

		CropMirageCropsBase crop;

		crop = (CropMirageCropsBase) cropSarracenia;
		crop.setStatusRegular(3, "Mirrgie Riana");
		crop.setStatusCrossing(0, 0, 3, 1, 2, "Sarracenia", "Green");
		crop.setMaxSize(5);
		crop.setHandlerSprites(
			new HandlerSpritesBasic(crop, maskMiragecrops4));
		crop.setHandlerHarvest(
			new HandlerHarvestOneItem(crop, new ItemStack(craftingLeaf)));

		crop = (CropMirageCropsBase) cropSpinach;
		crop.setStatusRegular(3, "Mirrgie Riana");
		crop.setStatusCrossing(0, 2, 0, 1, 0, "Spinach", "Green");
		crop.setMaxSize(4);
		crop.setHandlerSprites(
			new HandlerSpritesBasic(crop, maskMiragecrops4));
		crop.setHandlerHarvest(
			new HandlerHarvestOneItem(crop, new ItemStack(craftingLeaf)));

	}

}
