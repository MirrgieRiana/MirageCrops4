package mirrg.mir34.modding;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxy
{

	public boolean isServerSide();

	public void handle(FMLPreInitializationEvent event, Iterable<IModule> modules);

	public void handle(FMLInitializationEvent event, Iterable<IModule> modules);

	public void handle(FMLPostInitializationEvent event, Iterable<IModule> modules);

}
