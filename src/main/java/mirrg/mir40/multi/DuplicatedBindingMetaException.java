package mirrg.mir40.multi;

public class DuplicatedBindingMetaException extends RuntimeException
{

	public DuplicatedBindingMetaException(Metabase<?> metabase, int index, Object multi)
	{
		super("metabase: " + metabase + " index: " + index + " multi: " + multi);
	}

}
