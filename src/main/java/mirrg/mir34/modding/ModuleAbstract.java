package mirrg.mir34.modding;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ModuleAbstract implements IModule
{

	private final IMod mod;

	public ModuleAbstract(IMod mod)
	{
		this.mod = mod;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLPostInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLPreInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.SERVER)
	public void handlePreServer(FMLPostInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.SERVER)
	public void handlePreServer(FMLInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.SERVER)
	public void handlePreServer(FMLPreInitializationEvent event)
	{

	}

	@Override
	public void handle(FMLPostInitializationEvent event)
	{

	}

	@Override
	public void handle(FMLInitializationEvent event)
	{

	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClient(FMLPostInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClient(FMLInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClient(FMLPreInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.SERVER)
	public void handleServer(FMLPostInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.SERVER)
	public void handleServer(FMLInitializationEvent event)
	{

	}

	@Override
	@SideOnly(Side.SERVER)
	public void handleServer(FMLPreInitializationEvent event)
	{

	}

	@Override
	public final IMod getMod()
	{
		return mod;
	}

}
