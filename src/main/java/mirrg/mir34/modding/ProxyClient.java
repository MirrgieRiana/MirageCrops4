package mirrg.mir34.modding;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ProxyClient implements IProxy
{

	@Override
	public boolean isServerSide()
	{
		return false;
	}

	@Override
	public void handle(FMLPreInitializationEvent event, Iterable<IModule> modules)
	{
		for (IModule module : modules) {
			module.handlePreClient(event);
			module.handle(event);
			module.handleClient(event);
		}
	}

	@Override
	public void handle(FMLInitializationEvent event, Iterable<IModule> modules)
	{
		for (IModule module : modules) {
			module.handlePreClient(event);
			module.handle(event);
			module.handleClient(event);
		}
	}

	@Override
	public void handle(FMLPostInitializationEvent event, Iterable<IModule> modules)
	{
		for (IModule module : modules) {
			module.handlePreClient(event);
			module.handle(event);
			module.handleClient(event);
		}
	}

}
