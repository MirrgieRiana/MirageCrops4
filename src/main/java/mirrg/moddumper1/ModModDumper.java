package mirrg.moddumper1;

import mirrg.mir34.modding.IModule;
import mirrg.mir34.modding.ModAbstract;
import mirrg.moddumper1.core.dumper.ModuleDumper;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = ModModDumper.MODID,
	version = ModModDumper.VERSION,
	name = ModModDumper.NAME,
	dependencies = ModModDumper.DEPENDENCIES)
public class ModModDumper extends ModAbstract
{

	public static final String MODID = "mirrg_moddumper1";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "ModDumper";
	public static final String DEPENDENCIES =
		"";

	@Instance(ModModDumper.MODID)
	public static ModModDumper instance;

	@Override
	protected void loadModules()
	{
		addModule(new ModuleDumper(this));
	}

	public static Configuration configuration;

	/**
	 * 必ずオーバーライドしなければならない
	 */
	@Override
	@EventHandler
	public void handle(FMLPreInitializationEvent event)
	{
		configuration = new Configuration(event.getSuggestedConfigurationFile());
		configuration.load();

		super.handle(event);

		configuration.save();
		configuration = null;
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

	@EventHandler
	public void handle(FMLLoadCompleteEvent event)
	{
		for (IModule module : modules) {
			if (module instanceof ILoadCompleteHandler) {
				((ILoadCompleteHandler) module).handle(event);
			}
		}
	}

}
