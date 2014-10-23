package mirrg.miragecrops4.oregen.multi;

import java.util.Random;

import mirrg.mir40.block.Metablock;
import mirrg.mir40.multi.Multibase;
import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.ISlot;
import net.minecraft.world.World;

public class MetablockGlob<MULTI extends Multibase<MULTI, META>, META extends MetablockGlob<MULTI, META>>
	extends Metablock<MULTI, META>
{

	protected IGlob glob;
	protected ISlot slot;

	public MetablockGlob(IGlob glob, ISlot slot)
	{
		this.glob = glob;
		this.slot = slot;
	}

	public IGlob getGlob()
	{
		return glob;
	}

	public ISlot getSlot()
	{
		return slot;
	}

	protected void setGlob(IGlob glob)
	{
		this.glob = glob;
	}

	protected void setSlot(ISlot slot)
	{
		this.slot = slot;
	}

	@SuppressWarnings("rawtypes")
	public static class Raw extends MetablockGlob
	{

		public Raw(IGlob glob, ISlot slot)
		{
			super(glob, slot);
		}

	}

	public void updateTick(World world, int x, int y, int z, Random random)
	{

	}

}
