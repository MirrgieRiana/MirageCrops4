package mirrg.moddumper1.dump.dumpers;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import mirrg.mir40.crop.reflect.HelpersReflectCrop;
import mirrg.moddumper1.dump.IDumper;
import mirrg.moddumper1.dump.ILogger;

class DumperCropCrossingRate implements IDumper
{

	@Override
	public String getName()
	{
		return "dumperCropCrossingRate";
	}

	@Override
	public void onDump(final ILogger logger)
	{

		try {

			ICropTile tec = (ICropTile) HelpersReflectCrop.createTileEntityCrop();
			CropCard[] cards = Crops.instance.getCropList();

			{

				StringBuffer line = new StringBuffer();
				line.append("id");

				for (int j = 0; j < cards.length; j++) {
					if (cards[j] == null) continue;

					line.append(",");
					line.append(j);
				}

				logger.log(line.toString());
			}

			for (int i = 0; i < cards.length; i++) {
				if (cards[i] == null) continue;

				StringBuffer line = new StringBuffer();
				line.append(i);

				for (int j = 0; j < cards.length; j++) {
					if (cards[j] == null) continue;

					line.append(",");
					line.append(HelpersReflectCrop.calculateRatioFor(tec, cards[i], cards[j]));
				}

				logger.log(line.toString());
			}

		} catch (Exception e) {
			logger.log("Error: " + e.getClass() + ": " + e.toString());
		}

	}

}
