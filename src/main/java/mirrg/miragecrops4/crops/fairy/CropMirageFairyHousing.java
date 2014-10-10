package mirrg.miragecrops4.crops.fairy;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import net.minecraft.tileentity.TileEntity;

/**
 * CropTickごとに周囲9*9*3を検索して、{@link IFairyHirable}を発見次第妖精を派遣する。
 */
public class CropMirageFairyHousing extends CropMirageFairy<FairyTagHousing> implements ICropDataView
{

	@Override
	public void tick(ICropTile crop)
	{
		FairyTagHousing fairyTag = loadFairyTag(crop);
		if (fairyTag == null) {
			deleteFairyTag(crop);
			fairyTag = createFairyTag();
		}

		if (fairyTag.population > 0) {
			int hired = 0;

			end:
			for (int xi = -4; xi <= 4; xi++) {
				for (int yi = -1; yi <= 1; yi++) {
					for (int zi = -4; zi <= 4; zi++) {

						TileEntity te = crop.getWorld().getTileEntity(
							crop.getLocation().posX + xi,
							crop.getLocation().posY + yi,
							crop.getLocation().posZ + zi);

						if (te instanceof ICropTile) {
							ICropTile workplaceCropTile = (ICropTile) te;

							if (workplaceCropTile.getID() >= 0) {
								CropCard workplaceCropCard = Crops.instance.getCropList()[workplaceCropTile.getID()];

								if (workplaceCropCard != null && workplaceCropCard instanceof CropMirageFairyWorkplace) {
									IFairyHirable workplace = (IFairyHirable) workplaceCropCard;

									if (workplace.isHirable(workplaceCropTile)) {
										int capacity = workplace.getFairyCapacity(workplaceCropTile);

										if (fairyTag.population - hired <= capacity) {
											// 全員派遣

											int hire = fairyTag.population - hired;

											hired += hire;
											workplace.hire(workplaceCropTile, hire);

											break end;
										} else {
											// 一部派遣

											int hire = capacity;

											hired += hire;
											workplace.hire(workplaceCropTile, hire);

										}

									}

								}

							}

						}

					}
				}
			}

		}

		saveFairyTag(crop, fairyTag);
	}

	@Override
	public int getDataView(ICropTile crop)
	{
		FairyTagHousing fairyTag = loadFairyTag(crop);
		if (fairyTag == null) return 0;

		return fairyTag.population;
	}

	@Override
	protected FairyTagHousing createFairyTag()
	{
		FairyTagHousing fairyTag = new FairyTagHousing();
		fairyTag.populationMax = 5;
		fairyTag.population = 5;
		return fairyTag;
	}

	@Override
	protected int getCurrentVersion()
	{
		return 1;
	}

}
