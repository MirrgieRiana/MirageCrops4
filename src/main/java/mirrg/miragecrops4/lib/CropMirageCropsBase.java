package mirrg.miragecrops4.lib;

import ic2.api.crops.ICropTile;
import mirrg.mir40.crop.CropMirage;
import mirrg.mir40.crop.reflect.HelpersReflectCrop;
import net.minecraft.tileentity.TileEntity;

public class CropMirageCropsBase extends CropMirage
{

	int[][] sides = {
		{
			-1, 0
		}, {
			1, 0
		}, {
			0, -1
		}, {
			0, 1
		},
	};

	@Override
	public boolean canGrow(ICropTile crop)
	{
		if (HelpersReflectCrop.isUpgraded(crop)) {
			// 交配時

			for (int[] side : sides) {

				TileEntity tileEntity = crop.getWorld().getTileEntity(
					crop.getLocation().posX + side[0],
					crop.getLocation().posY,
					crop.getLocation().posZ + side[1]);

				if (tileEntity != null && tileEntity instanceof ICropTile) {
					ICropTile cropTile = (ICropTile) tileEntity;

					if (cropTile.getID() == crop.getID()) {
						return true;
					}

				}

			}

		}

		return super.canGrow(crop);
	}
}
