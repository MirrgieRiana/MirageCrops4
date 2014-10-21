package mirrg.miragecrops4.oregen;

import mirrg.mir40.block.Metablock;
import mirrg.mir40.multi.Multibase;
import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.ISlot;

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

	protected MetablockGlob()
	{

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

}
