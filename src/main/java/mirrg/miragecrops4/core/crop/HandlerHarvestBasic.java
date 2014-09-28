package mirrg.miragecrops4.core.crop;

import ic2.api.crops.ICropTile;

public class HandlerHarvestBasic extends HandlerHarvestBase
{

	public HandlerHarvestBasic(CropMirage cropMirage)
	{
		super(cropMirage);
	}

	@Override
	public int getOptimalHavestSize(ICropTile crop)
	{
		return cropMirage.maxSize();
	}

}
