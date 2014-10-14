package mirrg.mir40.item.glob;

import mirrg.mir40.glob.api.IGlob;
import mirrg.mir40.glob.api.ISlot;
import mirrg.mir40.item.MetaitemIcon;
import mirrg.mir40.multi.Multibase;

public class MetaitemIconGlob<MULTI extends Multibase<MULTI, META>, META extends MetaitemIconGlob<MULTI, META>>
	extends MetaitemIcon<MULTI, META>
{

	protected IGlob glob;
	protected ISlot slot;

	public MetaitemIconGlob(IGlob glob, ISlot slot)
	{
		this.glob = glob;
		this.slot = slot;
	}

	protected MetaitemIconGlob()
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
