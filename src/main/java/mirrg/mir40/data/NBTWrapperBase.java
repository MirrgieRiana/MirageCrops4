package mirrg.mir40.data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public abstract class NBTWrapperBase<TAG extends NBTBase, VALUE>
{

	private final String name;
	private final NBTWrapperCompound parent;

	/**
	 * ルートとして要素を定義する
	 */
	public NBTWrapperBase(String name)
	{
		this(name, null);
	}

	/**
	 * 親を与えて要素を定義する
	 */
	public NBTWrapperBase(String name, NBTWrapperCompound parent)
	{
		this.name = name;
		this.parent = parent;
	}

	public String getName()
	{
		return name;
	}

	public NBTWrapperCompound getParent()
	{
		return parent;
	}

	public abstract EnumNBTTypes getNbtType();

	/**
	 * 親を持つかどうか
	 */
	public boolean isRoot()
	{
		return parent == null;
	}

	/**
	 * 編集を行うべき、親要素が示すNBTタグを取得する。<br>
	 * 親要素が登録されていない場合、引数のNBTを出す。<br>
	 * 親要素の示すNBTタグが存在しない場合、nullを返す。
	 */
	public NBTTagCompound findParent(NBTTagCompound nbt)
	{
		return isRoot() ? nbt : parent.find(nbt);
	}

	/**
	 * 指定されたタグから再起的にたどって自身の表すタグを抽出する。<br>
	 * 型が異なる場合、見つからない扱いになる。見つからない場合、nullを返す。<br>
	 * この要素が親を持たない場合、{@link #extract(NBTTagCompound)}と同じことになる。
	 */
	public NBTBase find(NBTTagCompound nbt)
	{
		NBTTagCompound parentNbt = findParent(nbt);
		if (parentNbt == null) {
			// 親が存在しない
			return null;
		} else {
			return extract(parentNbt);
		}
	}

	/**
	 * 指定されたタグから再起的にたどらないで自身の表すタグを抽出する。<br>
	 * 型が異なる場合、見つからない扱いになる。見つからない場合、nullを返す。
	 */
	public NBTBase extract(NBTTagCompound parentNbt)
	{
		if (!isReadableFromParent(parentNbt)) {
			// 型の一致する要素が存在しない
			return null;
		} else {
			return parentNbt.getTag(name);
		}
	}

	/**
	 * 親要素が示すNBTタグが存在する場合に真を返す。<br>
	 * 大体{@link #find(NBTTagCompound)}と同じである。
	 */
	public boolean isWritable(NBTTagCompound nbt)
	{
		return findParent(nbt) != null;
	}

	/**
	 * 親要素が示すNBTタグが存在し、型が一致する要素を含む場合に真を返す。<br>
	 * 大体{@link #extract(NBTTagCompound)}と同じである。<br>
	 * これが真である場合、{@link #isWritable(NBTTagCompound)}も真である。
	 */
	public boolean isReadable(NBTTagCompound nbt)
	{
		return find(nbt) != null;
	}

	/**
	 * 引数が非nullの場合に真を返す。
	 */
	protected boolean isWritableToParent(NBTTagCompound parentNbt)
	{
		return parentNbt != null;
	}

	/**
	 * 引数のNBTタグに型の一致する要素が存在する場合に真を返す。<br>
	 * 引数のNBTタグがnullの場合に偽を返す。
	 */
	protected boolean isReadableFromParent(NBTTagCompound parentNbt)
	{
		if (parentNbt == null) return false;
		return parentNbt.hasKey(name, getNbtType().ordinal());
	}

	/**
	 * NBTタグからこの要素が示す要素を読みだす。<br>
	 * この要素が親要素を持っている場合、親要素を再帰的に取得して読みだす。<br>
	 * 親要素が示すタグが存在しない場合、nullを返す。<br>
	 * 型の一致する要素が存在しない場合、nullを返す。
	 */
	public VALUE readFromNBT(NBTTagCompound nbt)
	{
		NBTTagCompound parentNbt = findParent(nbt);
		return readFromParent(parentNbt);
	}

	/**
	 * NBTタグにこの要素が示す要素を書き出す。<br>
	 * この要素が親要素を持っている場合、親要素を再帰的に取得して書き出す。<br>
	 * 親要素が示すタグが存在しない場合、親要素を再起的に生成して書き出す。<br>
	 * 要素が存在しない場合、要素を生成して書き出す。<br>
	 * 型の一致しない要素が存在する場合、既存の要素を削除して書き出す。
	 */
	public void writeToNBT(NBTTagCompound nbt, VALUE value)
	{
		prepareParent(nbt);
		NBTTagCompound parentNbt = findParent(nbt);
		writeToParent(parentNbt, value);
	}

	/**
	 * 親要素が存在しない場合、親要素が存在している状態にする。
	 */
	protected void prepareParent(NBTTagCompound nbt)
	{
		parent.prepare(nbt);
	}

	/**
	 * 親要素が存在しない場合、nullを返す。<br>
	 * 型の一致する要素が存在しない場合、nullを返す。
	 */
	protected abstract VALUE readFromParent(NBTTagCompound parentNbt);

	/**
	 * 要素が存在しない場合、要素を生成して書き出す。<br>
	 * 型の一致しない要素が存在する場合、既存の要素を削除して書き出す。
	 */
	protected abstract void writeToParent(NBTTagCompound parentNbt, VALUE value);

}
