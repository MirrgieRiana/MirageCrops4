package mirrg.mir40.glob.api;

import mirrg.mir40.math.HelpersString;

public class HelpersGlob
{

	public static String getDictionaryName(ISlot slot, IGlob glob)
	{
		return slot.getName() + HelpersString.toUpperCaseHead(glob.getName());
	}

}
