package mirrg.mir34.modding;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ProxyServer implements IProxy
{

	@Override
	public boolean isServerSide()
	{
		return true;
	}

	@Override
	public void handle(FMLPreInitializationEvent event, Iterable<IModule> modules)
	{
		for (IModule module : modules) {
			module.handlePreServer(event);
			module.handle(event);
			module.handleServer(event);
		}
	}

	@Override
	public void handle(FMLInitializationEvent event, Iterable<IModule> modules)
	{
		for (IModule module : modules) {
			module.handlePreServer(event);
			module.handle(event);
			module.handleServer(event);
		}
	}

	@Override
	public void handle(FMLPostInitializationEvent event, Iterable<IModule> modules)
	{
		for (IModule module : modules) {
			module.handlePreServer(event);
			module.handle(event);
			module.handleServer(event);
		}
	}

}
