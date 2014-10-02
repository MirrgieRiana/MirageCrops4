package mirrg.mir40.crop;

import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class HandlerSeedBase implements IHandlerSeed
{

	protected final CropMirage cropMirage;

	public HandlerSeedBase(CropMirage cropMirage)
	{
		this.cropMirage = cropMirage;
	}

	@Override
	public ItemStack getSeeds(ICropTile crop)
	{
		return null;
	}

	@Override
	public float dropSeedChance(ICropTile crop)
	{
		if (crop.getSize() == 1) return 0;
		float base = 0.5F;
		if (crop.getSize() == 2) base /= 2F;
		for (int i = 0; i < cropMirage.tier(); i++) {
			base *= 0.8;
		}
		return base;
	}

}
