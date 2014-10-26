package mirrg.miragecrops4.oregen.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.item.api.IMetaitemIcon;
import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.ISlot;

public interface IMetaitemIconOregen<MULTI extends IMulti<MULTI, META>, META extends IMetaitemIconOregen<MULTI, META>>
	extends IMetaitemIcon<MULTI, META>
{

	public IGlob getGlob();

	public ISlot getSlot();

	public void setGlob(IGlob glob);

	public void setSlot(ISlot slot);

}
