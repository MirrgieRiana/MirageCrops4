package mirrg.miragecrops4.core.crop;

import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class HandlerHarvestBase implements IHandlerHarvest
{

	protected final CropMirage cropMirage;

	public HandlerHarvestBase(CropMirage cropMirage)
	{
		this.cropMirage = cropMirage;
	}

	@Override
	public ItemStack getGain(ICropTile crop)
	{
		return null;
	}

	@Override
	public float dropGainChance()
	{
		float base = 1F;
		for (int i = 0; i < cropMirage.tier(); i++) {
			base *= 0.95;
		}
		return base;
	}

	@Override
	public int getOptimalHavestSize(ICropTile crop)
	{
		return 1;
	}

	@Override
	public boolean canBeHarvested(ICropTile crop)
	{
		return crop.getSize() >= getOptimalHavestSize(crop);
	}

	@Override
	public byte getSizeAfterHarvest(ICropTile crop)
	{
		return 1;
	}

}
