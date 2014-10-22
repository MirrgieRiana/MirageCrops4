package mirrg.miragecrops4.oregen.multi;

import mirrg.mir40.item.MetaitemIcon;
import mirrg.mir40.multi.Multibase;
import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.ISlot;

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

	@SuppressWarnings("rawtypes")
	public static class Raw extends MetaitemIconGlob
	{

		public Raw(IGlob glob, ISlot slot)
		{
			super(glob, slot);
		}

	}

}
