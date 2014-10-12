package mirrg.moddumper1.core.dumper;

import java.util.HashSet;

import mirrg.moddumper1.IDumper;
import mirrg.moddumper1.ILogger;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;

public class DumperRecipeSorter implements IDumper
{

	@Override
	public String getName()
	{
		return "dumperRecipeSorter";
	}

	@Override
	public void onDump(final ILogger logger)
	{
		HashSet<Class<?>> scaned = new HashSet<Class<?>>();

		for (Object o : CraftingManager.getInstance().getRecipeList()) {

			if (!scaned.contains(o.getClass())) {
				scaned.add(o.getClass());

				if (o instanceof IRecipe) {
					IRecipe r = (IRecipe) o;

					logger.log("RECIPE CLASS,TOSTR,'%s',CN,'%s',CATE,'%s'",
						r,
						r.getClass().getName(),
						RecipeSorter.getCategory(r));

				} else {
					logger.log("Unknown Recipe Class: " + o + " : " + o.getClass().getName());
				}

			}

		}
	}

}
