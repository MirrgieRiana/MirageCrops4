package mirrg.moddumper1.core;

import mirrg.mir34.modding.IMod;

public class LoaderModules
{

	public static void loadModules(IMod mod)
	{
		mod.addModule(new ModuleDumperRecipeSorter(mod));
		mod.addModule(new ModuleDumperThaumCraft(mod));
		mod.addModule(new ModuleDumperRailCraftFuels(mod));
	}

}
