package mirrg.moddumper1.dump;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.moddumper1.ILoadCompleteHandler;
import mirrg.moddumper1.ModModDumper;
import mirrg.moddumper1.dump.dumpers.LoaderDumpers;
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
		FMLLog.info("parent directry path = %s", parentDir.getAbsolutePath());

		dumperWrappers = loadDumperWrappers(ModModDumper.configuration, "module", parentDir);
	}

	private static List<DumperWrapper> loadDumperWrappers(
		Configuration configuration, String categoryName, File parentDir)
	{
		List<IDumper> dumpers = getDumpers();
		FMLLog.info("all dumpers count = %s", dumpers.size());

		List<DumperWrapper> dumperWrappers = enableDumperWrappers(configuration, categoryName, dumpers);
		FMLLog.info("enabled dumpers count = %s", dumperWrappers.size());

		initDumperWrappers(dumperWrappers, parentDir);
		return dumperWrappers;
	}

	private static List<IDumper> getDumpers()
	{
		List<IDumper> dumpers = new ArrayList<IDumper>();
		LoaderDumpers.loadDumpers(dumpers);
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
		FMLLog.info("dumping... (FMLLoadCompleteEvent)");
		for (DumperWrapper dumperWrapper : dumperWrappers) {
			dumperWrapper.onDump();
		}
	}

}
