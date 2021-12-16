package mirrg.miragecrops4.fairy.multi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ITileEntityMirageMachine
{

	int getVirtualId();

	public boolean onBlockActivated(World world, int x, int y, int z,
		EntityPlayer player, int side, float x2, float y2, float z2);

}
