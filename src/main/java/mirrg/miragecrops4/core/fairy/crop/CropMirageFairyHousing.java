package mirrg.miragecrops4.core.fairy.crop;

import ic2.api.crops.ICropTile;

public class CropMirageFairyHousing extends CropMirageFairy<FairyTagHousing> implements ICropDataView
{

	@Override
	public void tick(ICropTile crop)
	{
		FairyTagHousing fairyTag = loadFairyTag(crop);
		if (fairyTag == null) {
			deleteFairyTag(crop);
			fairyTag = new FairyTagHousing();
		}

		fairyTag.population++;

		saveFairyTag(crop, fairyTag);
	}

	@Override
	public int getDataView(ICropTile crop)
	{
		FairyTagHousing fairyTag = loadFairyTag(crop);
		if (fairyTag != null) {
			return fairyTag.population;
		}

		return 0;
	}

	@Override
	protected FairyTagHousing createFairyTag()
	{
		return new FairyTagHousing();
	}

	@Override
	protected int getCurrentVersion()
	{
		return 1;
	}

}
