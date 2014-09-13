package mirrg.mir40.multi;

import mirrg.mir40.multi.api.IMeta;

public class Metabase<T extends Multibase> implements IMeta<T>
{

	T multi;

	@Override
	public T getMulti()
	{
		return multi;
	}

	int index;

	@Override
	public int getIndex()
	{
		return index;
	}

	boolean isBound = false;

	@Override
	public boolean isBound()
	{
		return isBound;
	}

	boolean isBindable()
	{
		return !isBound();
	}

	void bind(int index, T multi)
	{
		if (!isBindable()) throw new DuplicatedBindingMetaException(this, index, multi);
		this.index = index;
		this.multi = multi;
		isBound = true;
	}

}
