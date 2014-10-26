package mirrg.h.multi;

public class DuplicatedBindingToIndexException extends RuntimeException
{

	public DuplicatedBindingToIndexException(IMulti<?, ?> multi, int index, IMeta<?, ?> meta)
	{
		super("multi: " + multi + " index: " + index + " oldMeta: " + multi.getMeta(index) + " newMeta: " + meta);
	}

}
