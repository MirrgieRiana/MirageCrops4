package mirrg.miragecrops4.core.fairy.crop;

import ic2.api.crops.ICropTile;

public class CropMirageFairyWorkplace extends CropMirageFairy<FairyTagWorkplace> implements ICropDataView
{

	@Override
	public void tick(ICropTile crop)
	{
		FairyTagWorkplace fairyTag = loadFairyTag(crop);
		if (fairyTag == null) {
			deleteFairyTag(crop);
			fairyTag = new FairyTagWorkplace();
		}

		fairyTag.population++;

		saveFairyTag(crop, fairyTag);
	}

	@Override
	public int getDataView(ICropTile crop)
	{
		FairyTagWorkplace fairyTag = loadFairyTag(crop);
		if (fairyTag != null) {
			return fairyTag.population;
		}

		return 0;
	}

	@Override
	protected FairyTagWorkplace createFairyTag()
	{
		return new FairyTagWorkplace();
	}

	@Override
	protected int getCurrentVersion()
	{
		return 1;
	}

}
