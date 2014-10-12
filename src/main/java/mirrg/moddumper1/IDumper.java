package mirrg.moddumper1;

public interface IDumper
{

	/**
	 * ダンプファイル名と管理名
	 */
	public String getName();

	/**
	 * 出力はloggerに出す
	 */
	public void onDump(ILogger logger);

}
