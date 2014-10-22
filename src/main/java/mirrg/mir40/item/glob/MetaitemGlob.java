package mirrg.mir40.item.glob;

import mirrg.mir40.glob.api.IGlob;
import mirrg.mir40.glob.api.ISlot;
import mirrg.mir40.item.Metaitem;
import mirrg.mir40.multi.Multibase;

public class MetaitemGlob<MULTI extends Multibase<MULTI, META>, META extends MetaitemGlob<MULTI, META>>
	extends Metaitem<MULTI, META>
{

	protected IGlob glob;
	protected ISlot slot;

	public MetaitemGlob(IGlob glob, ISlot slot)
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
	public static class Raw extends MetaitemGlob
	{

		public Raw(IGlob glob, ISlot slot)
		{
			super(glob, slot);
		}

	}

}
