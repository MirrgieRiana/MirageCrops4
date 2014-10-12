package mirrg.mir34.modding;

import java.util.ArrayList;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class ModAbstract implements IMod
{

	@SidedProxy(clientSide = "mirrg.mir34.modding.ProxyClient", serverSide = "mirrg.mir34.modding.ProxyServer")
	public static IProxy proxy;

	public static boolean isServerSide()
	{
		return proxy.isServerSide();
	}

	private final String modId;
	private final String version;
	private final String name;
	private final String dependencies;

	public ModAbstract()
	{

		try {
			modId = (String) this.getClass().getField("MODID").get(null);
			version = (String) this.getClass().getField("VERSION").get(null);
			name = (String) this.getClass().getField("NAME").get(null);
			dependencies = (String) this.getClass().getField("DEPENDENCIES").get(null);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public final String getModId()
	{
		return modId;
	}

	@Override
	public final String getVersion()
	{
		return version;
	}

	@Override
	public final String getName()
	{
		return name;
	}

	@Override
	public final String getDependencies()
	{
		return dependencies;
	}

	protected ArrayList<IModule> modules = new ArrayList<IModule>();

	@Override
	public void addModule(IModule module)
	{
		modules.add(module);
	}

	protected abstract void loadModules();

	@EventHandler
	public void handle(FMLPreInitializationEvent event)
	{
		loadModules();
		proxy.handle(event, modules);
	}

	@EventHandler
	public void handle(FMLInitializationEvent event)
	{
		proxy.handle(event, modules);
	}

	@EventHandler
	public void handle(FMLPostInitializationEvent event)
	{
		proxy.handle(event, modules);
	}

	@Override
	public boolean isClient()
	{
		return !isServerSide();
	}

	@Override
	public boolean isServer()
	{
		return isServerSide();
	}

}
