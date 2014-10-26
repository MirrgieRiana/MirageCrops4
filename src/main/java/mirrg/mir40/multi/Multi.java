package mirrg.mir40.multi;

import java.util.Iterator;


public class Multi<MULTI extends IMulti<MULTI, META>, META extends IMeta<MULTI, META>>
	implements IMulti<MULTI, META>
{

	private final META[] metas;

	public Multi(META[] metas)
	{
		this.metas = metas;
	}

	@Override
	public boolean isBound(int index)
	{
		return metas[index] != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MULTI getMulti()
	{
		return (MULTI) this;
	}

	@Override
	public int getLength()
	{
		return metas.length;
	}

	@Override
	public META getMeta(int index)
	{
		META meta = isBound(index) ? metas[index] : metas[0];
		if (meta == null) throw new NullPointerException();
		return meta;
	}

	@Override
	public void bind(int index, META meta)
	{
		if (isBound(index)) throw new DuplicatedBindingToIndexException(this, index, meta);

		if (meta.isBound()) {
			if (meta.getMulti() != this) {
				// 不正な呼び出し

				throw new DuplicatedBindingMetaException(meta, index, this);
			} else {
				// 後にこっちのbindが呼ばれた

				metas[index] = meta;
			}
		} else {
			// 先にこっちのbindが呼ばれた

			metas[index] = meta; // A

			meta.bind(index, getMulti()); // Aの後
		}

	}

	@Override
	public boolean clearBindind(int index)
	{
		if (!isBound(index)) return false;

		if (metas[index].isBound()) {
			// 先にこっちのbindが呼ばれた

			META meta2 = metas[index];
			metas[index] = null; // A

			meta2.clearBindind(); // Aの後
		} else {
			// 後にこっちのbindが呼ばれた

			metas[index] = null;
		}

		return true;
	}

	// ----------------------- Iterable --------------------

	@Override
	public Iterator<META> iterator()
	{
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<META>
	{
		private int i = 0;

		public IteratorImpl()
		{
			seek();
		}

		@Override
		public boolean hasNext()
		{
			return i < getLength();
		}

		@Override
		public META next()
		{
			META t = getMeta(i);
			i++;
			seek();
			return t;
		}

		/**
		 * 直近のisBound(i)==trueとなる場所までiを移動させる。<br>
		 * 既にそうである場合、何もしない。<br>
		 * 何も得るものがない場合、iは値域外で停止する。
		 */
		private void seek()
		{
			while (hasNext() && !isBound(i)) {
				i++;
			}
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

}
