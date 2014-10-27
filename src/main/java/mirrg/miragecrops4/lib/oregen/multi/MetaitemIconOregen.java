package mirrg.miragecrops4.lib.oregen.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.MetaitemIcon;
import mirrg.mir41.glob.IGlob;
import mirrg.mir41.glob.ISlot;

public class MetaitemIconOregen<MULTI extends IMulti<MULTI, META>, META extends IMetaitemIconOregen<MULTI, META>>
	extends MetaitemIcon<MULTI, META> implements IMetaitemIconOregen<MULTI, META>
{

	protected IGlob glob;
	protected ISlot slot;

	public MetaitemIconOregen(IGlob glob, ISlot slot)
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

}
