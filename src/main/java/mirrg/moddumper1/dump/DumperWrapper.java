package mirrg.moddumper1.dump;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import cpw.mods.fml.common.FMLLog;

class DumperWrapper implements ILogger
{

	public DumperWrapper(IDumper dumper)
	{
		this.dumper = dumper;
	}

	private final IDumper dumper;
	private boolean finished = false;
	private PrintStream logStream;

	public void init(File parentDir)
	{
		File logFile = new File(parentDir, dumper.getName() + ".txt");
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
	public void log(String format, Object... args)
	{
		logStream.println(String.format(format, args));
	}

	public void onDump()
	{
		if (!finished) {
			finished = true;

			FMLLog.info("[%s] Dump Start", dumper.getName());
			log("// ###################### %s ######################", dumper.getName());
			dumper.onDump(this);
			FMLLog.info("[%s] Dump Finish", dumper.getName());
		}

	}

}
