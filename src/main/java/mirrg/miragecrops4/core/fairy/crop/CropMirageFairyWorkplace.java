package mirrg.miragecrops4.core.fairy.crop;

import static mirrg.mir40.crop.reflect.HelpersReflectCrop.*;
import ic2.api.crops.ICropTile;

/**
 * {@link IFairyHirable}であり、CropTickごとに派遣された分の妖精の労働効果を受理して妖精を再要請する。
 * 労働効果は成長として現れる。
 */
public class CropMirageFairyWorkplace extends CropMirageFairy<FairyTagWorkplace> implements ICropDataView,
	IFairyHirable
{

	@Override
	public void tick(ICropTile crop)
	{
		FairyTagWorkplace fairyTag = loadFairyTag(crop);
		if (fairyTag == null) {
			deleteFairyTag(crop);
			fairyTag = createFairyTag();
		}

		if (fairyTag.population > 0) {
			double rate = (double) fairyTag.population / fairyTag.populationMax;

			if (canGrow(crop)) {
				setGrowthPoints(crop, (int) (getGrowthPoints(crop) + calcGrowthRate(crop) * 5 * rate));
			}

			fairyTag.population = 0;
		}

		saveFairyTag(crop, fairyTag);
	}

	@Override
	public int getDataView(ICropTile crop)
	{
		FairyTagWorkplace fairyTag = loadFairyTag(crop);
		if (fairyTag == null) return 0;

		return fairyTag.population;
	}

	@Override
	protected FairyTagWorkplace createFairyTag()
	{
		FairyTagWorkplace fairyTag = new FairyTagWorkplace();
		fairyTag.populationMax = 10;
		fairyTag.population = 0;
		return fairyTag;
	}

	@Override
	protected int getCurrentVersion()
	{
		return 1;
	}

	@Override
	public boolean isHirable(ICropTile crop)
	{
		if (validateFairyTag(crop) == null) return false;
		return canGrow(crop);
	}

	@Override
	public int getFairyCapacity(ICropTile crop)
	{
		FairyTagWorkplace fairyTag = loadFairyTag(crop);
		return fairyTag.populationMax - fairyTag.population;
	}

	@Override
	public void hire(ICropTile crop, int population)
	{
		FairyTagWorkplace fairyTag = loadFairyTag(crop);
		fairyTag.population += population;
		saveFairyTag(crop, fairyTag);
	}

}
