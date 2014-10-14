package mirrg.mir40.multi.api;

public interface IMulti<MULTI extends IMulti<MULTI, META>, META extends IMeta<MULTI, META>>
{

	public int getLength();

	public boolean contains(int index);

	public META get(int index);

}
