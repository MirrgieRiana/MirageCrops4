package mirrg.h.multi;

public class DuplicatedBindingToIndexException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1534426143188797713L;

	public DuplicatedBindingToIndexException(IMulti<?, ?> multi, int index, IMeta<?, ?> meta)
	{
		super("multi: " + multi + " index: " + index + " oldMeta: " + multi.getMeta(index) + " newMeta: " + meta);
	}

}
