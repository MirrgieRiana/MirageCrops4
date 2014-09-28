package mirrg.miragecrops4.core.crop;

import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class HandlerSeedBasic extends HandlerSeedBase
{

	public HandlerSeedBasic(CropMirage cropMirage)
	{
		super(cropMirage);
	}

	@Override
	public ItemStack getSeeds(ICropTile crop)
	{
		return crop.generateSeeds(
			crop.getID(),
			crop.getGrowth(), crop.getGain(), crop.getResistance(),
			crop.getScanLevel());
	}

}
