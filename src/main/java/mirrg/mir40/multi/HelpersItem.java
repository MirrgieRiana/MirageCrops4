package mirrg.mir40.multi;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HelpersItem
{

	public static void dropAsEntity(World world, int x, int y, int z, ItemStack itemStack)
	{
		if (world.isRemote) return;
		if (itemStack == null) return;

		double f = 0.7D;
		double dx = world.rand.nextFloat() * f + (1.0D - f) * 0.5D;
		double dy = world.rand.nextFloat() * f + (1.0D - f) * 0.5D;
		double dz = world.rand.nextFloat() * f + (1.0D - f) * 0.5D;

		EntityItem entityItem = new EntityItem(world, x + dx, y + dy, z + dz, itemStack.copy());
		entityItem.delayBeforeCanPickup = 10;
		world.spawnEntityInWorld(entityItem);
	}

}
