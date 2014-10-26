package mirrg.h.multi;

public class DuplicatedBindingMetaException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8727487697628273264L;

	public DuplicatedBindingMetaException(IMeta<?, ?> meta, int index, IMulti<?, ?> multi)
	{
		super("meta: " + meta + " index: " + index + " oldMulti: " + meta.getMulti() + " newMulti: " + multi);
	}

}
