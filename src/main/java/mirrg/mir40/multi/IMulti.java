package mirrg.mir40.multi;

/**
 * 多数のスロットを持ち、スロットごとに同時に一つのメタアイテム（{@link IMeta}）に連結可能なコンテナ。<br>
 * スロットごとに未連結状態と連結状態が存在する（{@link #isBound(int)}）。<br>
 * インスタンス生成時は全てのスロットが未連結状態となっている。<br>
 * インスタンス生成時に設定したスロット数を変更することはできない。<br>
 * アイテムに連結しているとき、そのアイテムは自身のスロットに連結している。<br>
 * nullに対して連結することはできない。<br>
 * 未連結のスロットを取得しようとする場合、0番のスロットのアイテムが取得される。<br>
 * インデックス番号を超えるスロットに対するアクセスは例外が発生する。
 */
public interface IMulti<MULTI extends IMulti<MULTI, META>, META extends IMeta<MULTI, META>>
	extends Iterable<META>
{

	/**
	 * 現在の状態
	 *
	 * @return 連結状態の場合にtrue、未連結状態の場合にfalse
	 */
	public boolean isBound(int index);

	/**
	 * 自身をキャストしたもの
	 */
	public MULTI getMulti();

	/**
	 * コンテナのスロット数
	 */
	public int getLength();

	/**
	 * 連結状態の場合、アイテムを返す。<br>
	 * 実連結状態の場合、0番目のスロットのアイテムを返す。<br>
	 *
	 * @throws NullPointerException
	 *             0番目のスロットが未連結である場合にこれにアクセスした場合
	 */
	public META getMeta(int index);

	/**
	 * メタアイテムにこのインスタンスを連結する。<br>
	 * メタアイテムも連結状態になる。
	 *
	 * @throws DuplicatedBindingMetaException
	 *             指定のスロットが既に連結済みだった場合
	 * @throws DuplicatedBindingToIndexException
	 *             メタアイテムが既に連結状態だった場合
	 */
	public void bind(int index, META meta);

	/**
	 * 既存の連結を消去し、未連結状態に戻る。<br>
	 * 未連結状態である場合、何も行われない。<br>
	 * 連結していたメタアイテムも未連結状態に戻る。
	 *
	 * @return 連結の消去が行われた場合に真
	 */
	public boolean clearBindind(int index);

}
