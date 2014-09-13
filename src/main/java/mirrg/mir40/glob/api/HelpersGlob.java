package mirrg.mir40.glob.api;

import mirrg.mir40.math.HelpersString;

public class HelpersGlob
{

	public static String getDictionaryName(IGlob glob, ISlot slot)
	{
		return glob.getName() + HelpersString.toUpperCaseHead(slot.getName());
	}

}
