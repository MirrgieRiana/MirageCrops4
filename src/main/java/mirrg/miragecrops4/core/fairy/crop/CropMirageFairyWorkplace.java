package mirrg.miragecrops4.core.fairy.crop;

import ic2.api.crops.ICropTile;

/**
 * {@link IFairyHirable}であり、CropTickごとに派遣された分の妖精の労働効果を受理して妖精を再要請する。
 */
public class CropMirageFairyWorkplace extends CropMirageFairy<FairyTagWorkplace> implements ICropDataView, IFairyHirable
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

	@Override
	public boolean isHirable()
	{
		
		
		
		return false;
	}

	@Override
	public boolean getFairyCapacity()
	{
		
		
		return false;
	}

}
