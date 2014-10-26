package mirrg.mir40.block.glob;

import mirrg.mir40.block.Metablock;
import mirrg.mir40.block.api.IMetablock;
import mirrg.mir40.glob.api.IGlob;
import mirrg.mir40.glob.api.ISlot;
import mirrg.mir40.multi.api.IMulti;

public class MetablockGlob<MULTI extends IMulti<MULTI, META>, META extends IMetablock<MULTI, META>>
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

}
