package mirrg.moddumper1.dump.dumpers;

import java.util.List;

import mirrg.moddumper1.dump.IDumper;

public class LoaderDumpers
{

	public static void loadDumpers(List<IDumper> list)
	{
		list.add(new DumperRecipeSorter());
		list.add(new DumperThaumCraft());
		list.add(new DumperRailCraftFuels());
	}

}
