package mirrg.miragecrops4.core.crop;

import ic2.api.crops.ICropTile;

public class HandlerCrossBase implements IHandlerCross
{

	protected final CropMirage cropMirage;

	public HandlerCrossBase(CropMirage cropMirage)
	{
		this.cropMirage = cropMirage;
	}

	@Override
	public boolean canCross(ICropTile crop)
	{
		return crop.getSize() >= 3;
	}

}
