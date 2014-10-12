package mirrg.moddumper1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class ModuleDumperAbstract extends ModuleAbstract implements ILoadCompleteHandler
{

	public ModuleDumperAbstract(IMod mod)
	{
		super(mod);
	}

	protected boolean enabled = false;

	protected PrintStream logStream;

	@Override
	public void handle(FMLPreInitializationEvent event)
	{
		enabled = ((ModModDumper) getMod()).configuration.getBoolean(getModuleName(), "module", false,
			"if true, output dump data when LoadComplete");

		if (enabled) {
			prepareLogFile();
		}

	}

	protected void prepareLogFile()
	{
		File logFile = new File(
			Minecraft.getMinecraft().mcDataDir, getMod().getModId() + "/" + getModuleName() + ".txt");
		if (!logFile.exists()) {
			File dir = logFile.getParentFile();
			if (!dir.exists()) {
				if (!dir.mkdirs()) {
					new RuntimeException("could not make " + dir).printStackTrace();
					return;
				}
			}
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		try {
			logStream = new PrintStream(logFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void handle(FMLLoadCompleteEvent event)
	{
		processDump();
	}

	/**
	 * ダンプ命令。コンフィグで処理内容が変わる
	 */
	protected void processDump()
	{
		if (enabled) {
			info("[%s] Dump Start", getModuleName());
			log("// ###################### %s ######################", getModuleName());
			onDump();
			info("[%s] Dump Finish", getModuleName());
		} else {
			info("[%s] Dump Canceled", getModuleName());
		}
	}

	/**
	 * ダンプ処理。{@link ModuleDumperAbstract#processDump}から呼び出される
	 */
	protected abstract void onDump();

	protected void log(String format, Object... args)
	{
		logStream.println(String.format(format, args));
	}

	protected void info(String format, Object... args)
	{
		FMLLog.info(format, args);
	}

}
