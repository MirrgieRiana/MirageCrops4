package mirrg.miragecrops4.core.crop;

import ic2.api.crops.ICropTile;

public interface IHandlerGrow
{

	public boolean canGrow(ICropTile crop);

	public int growthDuration(ICropTile crop);

	public int getrootslength(ICropTile crop);

	public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air);

}
