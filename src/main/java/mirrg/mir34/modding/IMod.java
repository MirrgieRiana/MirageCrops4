package mirrg.mir34.modding;

public interface IMod
{

	public String getModId();

	public String getVersion();

	public String getName();

	public String getDependencies();

	public void addModule(IModule iModule);

	public boolean isClient();

	public boolean isServer();

}
