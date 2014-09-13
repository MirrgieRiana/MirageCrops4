package mirrg.mir34.modding;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IModule
{

	public IMod getMod();

	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLPostInitializationEvent event);

	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLInitializationEvent event);

	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLPreInitializationEvent event);

	@SideOnly(Side.SERVER)
	public void handlePreServer(FMLPostInitializationEvent event);

	@SideOnly(Side.SERVER)
	public void handlePreServer(FMLInitializationEvent event);

	@SideOnly(Side.SERVER)
	public void handlePreServer(FMLPreInitializationEvent event);

	public void handle(FMLPreInitializationEvent event);

	public void handle(FMLInitializationEvent event);

	public void handle(FMLPostInitializationEvent event);

	@SideOnly(Side.CLIENT)
	public void handleClient(FMLPostInitializationEvent event);

	@SideOnly(Side.CLIENT)
	public void handleClient(FMLInitializationEvent event);

	@SideOnly(Side.CLIENT)
	public void handleClient(FMLPreInitializationEvent event);

	@SideOnly(Side.SERVER)
	public void handleServer(FMLPostInitializationEvent event);

	@SideOnly(Side.SERVER)
	public void handleServer(FMLInitializationEvent event);

	@SideOnly(Side.SERVER)
	public void handleServer(FMLPreInitializationEvent event);

	public String getModuleName();

}
