package mirrg.mir40.multi.api;

public interface IMulti<T extends IMeta>
{

	public int getLength();

	public boolean contains(int index);

	public T get(int index);

}
