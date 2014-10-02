package mirrg.mir40.crop;

import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public interface IHandlerSeed
{

	public ItemStack getSeeds(ICropTile crop);

	public float dropSeedChance(ICropTile crop);

}
