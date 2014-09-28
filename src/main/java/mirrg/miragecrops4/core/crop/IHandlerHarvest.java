package mirrg.miragecrops4.core.crop;

import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public interface IHandlerHarvest
{

	public ItemStack getGain(ICropTile crop);

	public float dropGainChance();

	public int getOptimalHavestSize(ICropTile crop);

	public boolean canBeHarvested(ICropTile crop);

	public byte getSizeAfterHarvest(ICropTile crop);

}
