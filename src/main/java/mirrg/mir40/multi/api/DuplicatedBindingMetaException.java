package mirrg.mir40.multi.api;

public class DuplicatedBindingMetaException extends RuntimeException
{

	public DuplicatedBindingMetaException(IMeta<?, ?> meta, int index, IMulti<?, ?> multi)
	{
		super("meta: " + meta + " index: " + index + " oldMulti: " + meta.getMulti() + " newMulti: " + multi);
	}

}
