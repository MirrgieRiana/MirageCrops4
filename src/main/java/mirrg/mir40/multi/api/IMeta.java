package mirrg.mir40.multi.api;

import mirrg.eclipse.annotation.Nullable;

/**
 * 同時に一つの親コンテナ（{@link IMulti}）のスロットに連結可能なメタアイテム。<br>
 * 未連結状態と連結状態が存在する（{@link #isBound()}）。<br>
 * インスタンス生成時は未連結状態となっている。<br>
 * コンテナのスロットに連結しているとき、そのコンテナのスロットは自身に連結している。<br>
 * nullに対して連結することはできない。
 */
public interface IMeta<MULTI extends IMulti<MULTI, META>, META extends IMeta<MULTI, META>>
{

	/**
	 * 現在の状態
	 *
	 * @return 連結状態の場合にtrue、未連結状態の場合にfalse
	 */
	public boolean isBound();

	/**
	 * 連結状態の場合、親コンテナを返す。<br>
	 * 実連結状態の場合、nullを返す。
	 */
	@Nullable
	public MULTI getMulti();

	/**
	 * 自身をキャストしたもの
	 */
	public META getMeta();

	/**
	 * 連結された親コンテナの連結先スロットインデックスを返す。
	 *
	 * @throws NullPointerException
	 *             このオブジェクトが未連結の場合
	 */
	public int getIndex();

	/**
	 * 親コンテナのスロットにこのインスタンスを連結する。<br>
	 * 親コンテナのスロットも連結状態になる。
	 *
	 * @throws DuplicatedBindingMetaException
	 *             親コンテナのスロットが既に連結済みだった場合
	 * @throws DuplicatedBindingToIndexException
	 *             このオブジェクトが既に連結状態だった場合
	 */
	public void bind(int index, MULTI multi);

	/**
	 * 既存の連結を消去し、未連結状態に戻る。<br>
	 * 未連結状態である場合、何も行われない。<br>
	 * 親コンテナの連結先スロットも未連結状態に戻る。
	 *
	 * @return 連結の消去が行われた場合に真
	 */
	public boolean clearBindind();

}
