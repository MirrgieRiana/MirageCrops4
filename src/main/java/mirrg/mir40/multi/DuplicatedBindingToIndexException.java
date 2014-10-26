package mirrg.mir40.multi;

import mirrg.mir40.multi.api.IMeta;
import mirrg.mir40.multi.api.IMulti;

public class DuplicatedBindingToIndexException extends RuntimeException
{

	public DuplicatedBindingToIndexException(IMulti<?, ?> multi, int index, IMeta<?, ?> meta)
	{
		super("multi: " + multi + " index: " + index + " oldMeta: " + multi.getMeta(index) + " newMeta: " + meta);
	}

}
