package mirrg.mir40.multi;

import java.util.Iterator;

import mirrg.mir40.multi.api.IMulti;

public class Multibase<T extends Metabase> implements IMulti<T>, Iterable<T>
{

	public Multibase(int length)
	{
		metas = (T[]) new Metabase[length];
	}

	public void bind(int index, T meta)
	{
		if (contains(index)) throw new DuplicatedBindingToIndexException(this, index);
		if (!meta.isBindable()) throw new DuplicatedBindingMetaException(meta, index, this);
		meta.bind(index, this);
		metas[index] = meta;
	}

	// ----------------------- IMulti --------------------

	private T[] metas;

	@Override
	public int getLength()
	{
		return metas.length;
	}

	@Override
	public boolean contains(int index)
	{
		return metas[index] != null;
	}

	@Override
	public T get(int index)
	{
		if (contains(index)) return metas[index];
		return metas[0];
	}

	// ----------------------- Iterable --------------------

	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>() {

			private int i = 0;

			@Override
			public boolean hasNext()
			{
				return i < getLength();
			}

			@Override
			public T next()
			{
				T t = get(i);
				i++;
				while (hasNext() && !contains(i)) {
					i++;
				}
				return t;
			}

			@Override
			public void remove()
			{
				throw new UnsupportedOperationException();
			}

		};
	}

}
