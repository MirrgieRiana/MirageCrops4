package mirrg.mir40.crop.reflect;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;

public class HelpersCrop
{

	public static void putCrop(int id, CropCard addition)
	{
		CropCard existing = Crops.instance.getCropList()[id];
		if (existing != null) throw new RuntimeException();

		Crops.instance.getCropList()[id] = addition;

	}

}
