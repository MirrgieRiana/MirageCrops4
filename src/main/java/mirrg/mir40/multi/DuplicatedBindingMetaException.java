package mirrg.mir40.multi;

import mirrg.mir40.multi.api.IMeta;
import mirrg.mir40.multi.api.IMulti;

public class DuplicatedBindingMetaException extends RuntimeException
{

	public DuplicatedBindingMetaException(IMeta<?, ?> meta, int index, IMulti<?, ?> multi)
	{
		super("meta: " + meta + " index: " + index + " oldMulti: " + meta.getMulti() + " newMulti: " + multi);
	}

}
