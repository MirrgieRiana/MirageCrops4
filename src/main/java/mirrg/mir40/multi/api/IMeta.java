package mirrg.mir40.multi.api;

public interface IMeta<MULTI extends IMulti<MULTI, META>, META extends IMeta<MULTI, META>>
{

	public MULTI getMulti();

	public int getIndex();

	public boolean isBound();

}
