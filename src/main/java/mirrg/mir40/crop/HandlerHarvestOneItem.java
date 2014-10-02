package mirrg.mir40.crop;

import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class HandlerHarvestOneItem extends HandlerHarvestBasic
{

	protected ItemStack gain;

	public HandlerHarvestOneItem(CropMirage cropMirage)
	{
		super(cropMirage);
	}

	public HandlerHarvestOneItem(CropMirage cropMirage, ItemStack gain)
	{
		this(cropMirage);
		setGain(gain);
	}

	public void setGain(ItemStack gain)
	{
		this.gain = gain;
	}

	@Override
	public ItemStack getGain(ICropTile crop)
	{
		if (gain == null) return null;
		return gain.copy();
	}

}
