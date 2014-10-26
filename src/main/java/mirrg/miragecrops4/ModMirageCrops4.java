package mirrg.miragecrops4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.IModule;
import mirrg.mir34.modding.ModAbstract;
import mirrg.mir40.math.HelpersString;
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
		addModule(createModule(getClass().getPackage().getName() + ".%s", "Module%s", "core"));
		addModule(createModule(getClass().getPackage().getName() + ".%s", "Module%s", "oregen"));
		addModule(createModule(getClass().getPackage().getName() + ".%s", "Module%s", "fairy"));
		addModule(createModule(getClass().getPackage().getName() + ".%s", "Module%s", "crops"));
	}

	@SuppressWarnings("unchecked")
	private IModule createModule(String packageFormat, String classFormat, String module)
	{
		String packageName = String.format(packageFormat, module);
		String className = String.format(classFormat, HelpersString.toUpperCaseHead(module));

		Class<? extends IModule> clazz;

		try {
			clazz = (Class<? extends IModule>) Class.forName(packageName + "." + className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		Constructor<? extends IModule> constructor;
		try {
			constructor = clazz.getConstructor(IMod.class);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}

		IModule object;
		try {
			object = constructor.newInstance(this);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		return object;
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
