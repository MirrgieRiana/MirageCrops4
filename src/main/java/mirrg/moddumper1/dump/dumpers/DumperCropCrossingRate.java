package mirrg.moddumper1.dump.dumpers;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

		ICropTile tec = (ICropTile) HelpersReflectCrop.createTileEntityCrop();
		CropCard[] cards = Crops.instance.getCropList();

		List<CropCard> cards2 = new ArrayList<CropCard>();

		for (int i = 0; i < cards.length; i++) {
			if (cards[i] != null) {
				cards2.add(cards[i]);
			}
		}

		logger.log("[Id Order]");
		drawRatios(logger, tec, cards2);

		Collections.sort(cards2, new Comparator<CropCard>() {

			@Override
			public int compare(CropCard o1, CropCard o2)
			{
				if (o1.tier() == o2.tier()) return 0;
				return o1.tier() > o2.tier() ? 1 : -1;
			}

		});

		logger.log("[Tier Order]");
		drawRatios(logger, tec, cards2);

	}

	private void drawRatios(final ILogger logger, ICropTile tec, List<CropCard> cards)
	{
		{
			StringBuffer line = new StringBuffer();
			line.append("id,*,*");

			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j) == null) continue;

				line.append(",");
				line.append(j);
			}

			logger.log(line.toString());
		}

		{
			StringBuffer line = new StringBuffer();
			line.append("*,name,*");

			for (int j = 0; j < cards.size(); j++) {
				line.append(",");
				line.append(cards.get(j).name());
			}

			logger.log(line.toString());
		}

		{
			StringBuffer line = new StringBuffer();
			line.append("*,*,tier");

			for (int j = 0; j < cards.size(); j++) {
				line.append(",");
				line.append(cards.get(j).tier());
			}

			logger.log(line.toString());
		}

		for (int i = 0; i < cards.size(); i++) {
			StringBuffer line = new StringBuffer();
			line.append(i);
			line.append(",");
			line.append(cards.get(i).name());
			line.append(",");
			line.append(cards.get(i).tier());

			for (int j = 0; j < cards.size(); j++) {
				line.append(",");
				line.append(HelpersReflectCrop.calculateRatioFor(tec, cards.get(i), cards.get(j)));
			}

			logger.log(line.toString());
		}
	}

}
