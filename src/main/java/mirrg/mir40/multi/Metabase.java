package mirrg.mir40.multi;

import mirrg.mir40.multi.api.IMeta;

public class Metabase<MULTI extends Multibase<MULTI, META>, META extends Metabase<MULTI, META>>
	implements IMeta<MULTI, META>
{

	MULTI multi;

	@Override
	public MULTI getMulti()
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

	void bind(int index, MULTI multi)
	{
		if (!isBindable()) throw new DuplicatedBindingMetaException(this, index, multi);
		this.index = index;
		this.multi = multi;
		isBound = true;
	}

}
