package mirrg.mir40.multi.api;

public interface IMeta<T extends IMulti>
{

	public T getMulti();

	public int getIndex();

	public boolean isBound();

}
