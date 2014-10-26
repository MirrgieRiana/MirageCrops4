package mirrg.miragecrops4.oregen.multi;

import java.util.Random;

import mirrg.mir40.block.Metablock;
import mirrg.mir40.multi.IMulti;
import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.ISlot;
import net.minecraft.world.World;

public class MetablockOregen<MULTI extends IMulti<MULTI, META>, META extends IMetablockOregen<MULTI, META>>
	extends Metablock<MULTI, META> implements IMetablockOregen<MULTI, META>
{

	protected IGlob glob;
	protected ISlot slot;

	public MetablockOregen(IGlob glob, ISlot slot)
	{
		this.glob = glob;
		this.slot = slot;
	}

	@Override
	public IGlob getGlob()
	{
		return glob;
	}

	@Override
	public ISlot getSlot()
	{
		return slot;
	}

	@Override
	public void setGlob(IGlob glob)
	{
		this.glob = glob;
	}

	@Override
	public void setSlot(ISlot slot)
	{
		this.slot = slot;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{

	}

}
