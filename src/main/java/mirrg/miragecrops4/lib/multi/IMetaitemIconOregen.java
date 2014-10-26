package mirrg.miragecrops4.lib.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.IMetaitemIcon;
import mirrg.mir41.glob.IGlob;
import mirrg.mir41.glob.ISlot;

public interface IMetaitemIconOregen<MULTI extends IMulti<MULTI, META>, META extends IMetaitemIconOregen<MULTI, META>>
	extends IMetaitemIcon<MULTI, META>
{

	public IGlob getGlob();

	public ISlot getSlot();

	public void setGlob(IGlob glob);

	public void setSlot(ISlot slot);

}
