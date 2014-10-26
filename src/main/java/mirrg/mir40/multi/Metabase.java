package mirrg.mir40.multi;

import mirrg.eclipse.annotation.Nullable;

public class Metabase<MULTI extends IMulti<MULTI, META>, META extends IMeta<MULTI, META>>
	implements IMeta<MULTI, META>
{

	@Nullable
	private MULTI multi;

	@Override
	public boolean isBound()
	{
		return multi != null;
	}

	@Override
	@Nullable
	public MULTI getMulti()
	{
		return multi;
	}

	@SuppressWarnings("unchecked")
	@Override
	public META getMeta()
	{
		return (META) this;
	}

	private int index;

	@Override
	public int getIndex()
	{
		if (!isBound()) throw new NullPointerException();
		return index;
	}

	@Override
	public void bind(int index, MULTI multi)
	{
		if (isBound()) throw new DuplicatedBindingMetaException(this, index, multi);

		if (multi.isBound(index)) {
			if (multi.getMeta(index) != this) {
				// 不正な呼び出し

				throw new DuplicatedBindingToIndexException(multi, index, this);
			} else {
				// 後にこっちのbindが呼ばれた

				this.index = index;
				this.multi = multi;
			}
		} else {
			// 先にこっちのbindが呼ばれた

			this.index = index; // A
			this.multi = multi;

			multi.bind(index, getMeta()); // Aの後
		}

	}

	@Override
	public boolean clearBindind()
	{
		if (!isBound()) return false;

		if (multi.isBound(index)) {
			// 先にこっちのbindが呼ばれた

			MULTI multi2 = multi;
			multi = null; // A

			multi2.clearBindind(index); // Aの後
		} else {
			// 後にこっちのbindが呼ばれた

			multi = null;
		}

		return true;
	}

}
