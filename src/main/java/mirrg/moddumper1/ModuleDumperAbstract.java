package mirrg.moddumper1;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
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

	@Override
	public void handle(FMLPreInitializationEvent event)
	{
		enabled = ((ModModDumper) getMod()).configuration.getBoolean(getModuleName(), "module", false,
			"if true, output dump data when LoadComplete");
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
			log("[%s] Dump Start", getModuleName());
			onDump();
			log("[%s] Dump Start", getModuleName());
		} else {
			log("[%s] Dump Canceled", getModuleName());
		}
	}

	/**
	 * ダンプ処理。{@link ModuleDumperAbstract#processDump}から呼び出される
	 */
	protected abstract void onDump();

	protected void log(String format, Object... args)
	{
		FMLLog.info(format, args);
	}

}
