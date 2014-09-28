package mirrg.miragecrops4.core.crop;

import ic2.api.crops.ICropTile;

public class HandlerGrowBase implements IHandlerGrow
{

	protected final CropMirage cropMirage;

	public HandlerGrowBase(CropMirage cropMirage)
	{
		this.cropMirage = cropMirage;
	}

	@Override
	public boolean canGrow(ICropTile crop)
	{
		return crop.getSize() < cropMirage.maxSize();
	}

	@Override
	public int growthDuration(ICropTile crop)
	{
		return cropMirage.tier() * 200;
	}

	@Override
	public int getrootslength(ICropTile crop)
	{
		return 1;
	}

	@Override
	public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air)
	{
		return (int) (humidity + nutrients + air);
	}

}
