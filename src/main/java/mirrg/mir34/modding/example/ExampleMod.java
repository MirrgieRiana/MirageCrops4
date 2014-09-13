package mirrg.mir34.modding.example;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModAbstract;
import mirrg.mir34.modding.ModuleAbstract;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

// @Mod(
// modid = ExampleMod.MODID,
// version = ExampleMod.VERSION,
// name = ExampleMod.NAME,
// dependencies = ExampleMod.DEPENDENCIES)
public class ExampleMod extends ModAbstract
{

	public static final String MODID = "mirrg_example_mod";
	public static final String VERSION = "0.0.1";
	public static final String NAME = "MirageExampleMod";
	public static final String DEPENDENCIES =
		"required-after:IC2;" +
			"";

	@Instance(ExampleMod.MODID)
	public static ExampleMod instance;

	@Override
	protected void loadModules()
	{
		addModule(new ExampleModule(this));
	}

	/**
	 * 必ずオーバーライドしなければならない
	 */
	@Override
	@EventHandler
	public void handle(FMLPreInitializationEvent event)
	{
		super.handle(event);
	}

	/**
	 * 必ずオーバーライドしなければならない
	 */
	@Override
	@EventHandler
	public void handle(FMLInitializationEvent event)
	{
		super.handle(event);
	}

	/**
	 * 必ずオーバーライドしなければならない
	 */
	@Override
	@EventHandler
	public void handle(FMLPostInitializationEvent event)
	{
		super.handle(event);
	}

	public static class ExampleModule extends ModuleAbstract
	{

		public ExampleModule(IMod mod)
		{
			super(mod);
		}

		@Override
		public void handle(FMLPostInitializationEvent event)
		{
			FMLLog.info("Pre Init");
		}

		@Override
		public void handle(FMLInitializationEvent event)
		{
			FMLLog.info("Init");
		}

		@Override
		public void handle(FMLPreInitializationEvent event)
		{
			FMLLog.info("Post Init");
		}

		@Override
		public String getModuleName()
		{
			return "exampleModule";
		}

	}

}
