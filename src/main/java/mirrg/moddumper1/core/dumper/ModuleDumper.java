package mirrg.moddumper1.core.dumper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.moddumper1.DumperWrapper;
import mirrg.moddumper1.IDumper;
import mirrg.moddumper1.ILoadCompleteHandler;
import mirrg.moddumper1.ModModDumper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleDumper extends ModuleAbstract implements ILoadCompleteHandler
{

	public ModuleDumper(IMod mod)
	{
		super(mod);
	}

	@Override
	public String getModuleName()
	{
		return "dumper";
	}

	//

	protected List<DumperWrapper> dumperWrappers;

	@Override
	public void handle(FMLPreInitializationEvent event)
	{
		File parentDir = new File(Minecraft.getMinecraft().mcDataDir, getMod().getModId());
		dumperWrappers = loadDumperWrappers(((ModModDumper) getMod()).configuration, "module", parentDir);
	}

	private static List<DumperWrapper> loadDumperWrappers(
		Configuration configuration, String categoryName, File parentDir)
	{
		List<DumperWrapper> dumperWrappers = enableDumperWrappers(configuration, categoryName, getDumpers());
		initDumperWrappers(dumperWrappers, parentDir);
		return dumperWrappers;
	}

	private static List<IDumper> getDumpers()
	{
		List<IDumper> dumpers = new ArrayList<IDumper>();
		LoaderModules.loadModules(dumpers);
		return dumpers;
	}

	private static ArrayList<DumperWrapper> enableDumperWrappers(
		Configuration configuration, String categoryName, List<IDumper> dumpers)
	{
		ArrayList<DumperWrapper> dumperWrappers = new ArrayList<DumperWrapper>();

		for (IDumper dumper : dumpers) {
			DumperWrapper dumperWrapper = new DumperWrapper(dumper);

			if (isEnable(configuration, categoryName, dumper)) {
				dumperWrappers.add(dumperWrapper);
				FMLLog.info("dumper module [%s] enabled", dumper.getName());
			} else {
				FMLLog.info("dumper module [%s] disabled", dumper.getName());
			}

		}

		return dumperWrappers;
	}

	private static boolean isEnable(Configuration configuration, String categoryName, IDumper dumper)
	{
		return configuration.getBoolean(
			dumper.getName(),
			categoryName,
			false,
			"if true, output dump data when LoadComplete");
	}

	private static void initDumperWrappers(
		List<DumperWrapper> dumperWrappers, File parentDir)
	{
		for (DumperWrapper dumperWrapper : dumperWrappers) {
			dumperWrapper.init(parentDir);
		}
	}

	@Override
	public void handle(FMLLoadCompleteEvent event)
	{
		for (DumperWrapper dumperWrapper : dumperWrappers) {
			dumperWrapper.onDump();
		}
	}

}
