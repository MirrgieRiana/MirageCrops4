package mirrg.moddumper1.dump;

import java.io.PrintStream;

public interface ILogger
{

	/**
	 * ダンプファイルに出力
	 *
	 * @param format
	 *            {@link String#format(String, Object...)}
	 */
	public void log(String format, Object... objects);

	public PrintStream getPrintStream();

}
