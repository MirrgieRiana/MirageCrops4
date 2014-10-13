package mirrg.mir40.item.glob;

import mirrg.mir40.glob.api.IGlob;
import mirrg.mir40.glob.api.ISlot;
import mirrg.mir40.item.Metaitem;

public class MetaitemGlob extends Metaitem
{

	protected IGlob glob;
	protected ISlot slot;

	public MetaitemGlob(IGlob glob, ISlot slot)
	{
		this.glob = glob;
		this.slot = slot;
	}

	protected MetaitemGlob()
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
