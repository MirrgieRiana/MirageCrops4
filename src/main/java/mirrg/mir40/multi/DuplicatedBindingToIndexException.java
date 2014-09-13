package mirrg.mir40.multi;

public class DuplicatedBindingToIndexException extends RuntimeException
{

	public DuplicatedBindingToIndexException(Multibase<?> multibase, int index)
	{
		super("multibase: " + multibase + " index: " + index);
	}

}
