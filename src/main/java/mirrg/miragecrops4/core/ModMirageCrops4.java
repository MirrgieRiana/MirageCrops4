package mirrg.miragecrops4.core;

import mirrg.mir34.modding.ModAbstract;
import mirrg.miragecrops4.crops.ModuleCrops;
import mirrg.miragecrops4.fairy.ModuleFairy;
import mirrg.miragecrops4.oregen.ModuleOregen;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = ModMirageCrops4.MODID,
	version = ModMirageCrops4.VERSION,
	name = ModMirageCrops4.NAME,
	dependencies = ModMirageCrops4.DEPENDENCIES)
public class ModMirageCrops4 extends ModAbstract
{

	public static final String MODID = "miragecrops4";
	public static final String VERSION = "4.0.4";
	public static final String NAME = "MirageCrops4";
	public static final String DEPENDENCIES =
		"after:IC2;" +
			"";

	@Instance(ModMirageCrops4.MODID)
	public static ModMirageCrops4 instance;

	@Override
	protected void loadModules()
	{
		addModule(new ModuleCore(this));
		addModule(new ModuleOregen(this));
		addModule(new ModuleFairy(this));
		addModule(new ModuleCrops(this));
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

}
