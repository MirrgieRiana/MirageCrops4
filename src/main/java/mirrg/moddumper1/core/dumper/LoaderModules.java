package mirrg.moddumper1.core.dumper;

import java.util.List;

import mirrg.moddumper1.IDumper;

public class LoaderModules
{

	public static void loadModules(List<IDumper> list)
	{
		list.add(new DumperRecipeSorter());
		list.add(new DumperThaumCraft());
		list.add(new DumperRailCraftFuels());
	}

}
