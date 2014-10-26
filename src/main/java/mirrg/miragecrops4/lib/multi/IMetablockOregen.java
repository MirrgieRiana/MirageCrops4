package mirrg.miragecrops4.lib.multi;

import java.util.Random;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.IMetablock;
import mirrg.mir41.glob.IGlob;
import mirrg.mir41.glob.ISlot;
import net.minecraft.world.World;

public interface IMetablockOregen<MULTI extends IMulti<MULTI, META>, META extends IMetablockOregen<MULTI, META>>
	extends IMetablock<MULTI, META>
{

	public IGlob getGlob();

	public ISlot getSlot();

	public void setGlob(IGlob glob);

	public void setSlot(ISlot slot);

	public void updateTick(World world, int x, int y, int z, Random random);

}
