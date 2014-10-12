package mirrg.moddumper1.core;

import java.util.HashSet;

import mirrg.mir34.modding.IMod;
import mirrg.moddumper1.ModuleDumperAbstract;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class ModuleDumperRecipeSorter extends ModuleDumperAbstract
{

	public ModuleDumperRecipeSorter(IMod mod)
	{
		super(mod);
	}

	@Override
	public String getModuleName()
	{
		return "dumperRecipeSorter";
	}

	@Override
	public void handle(FMLPostInitializationEvent event)
	{
		super.handle(event);

		processDump();
	}

	@Override
	protected void onDump()
	{
		HashSet<Class<?>> scaned = new HashSet<Class<?>>();

		for (Object o : CraftingManager.getInstance().getRecipeList()) {

			if (!scaned.contains(o.getClass())) {
				scaned.add(o.getClass());

				if (o instanceof IRecipe) {
					IRecipe r = (IRecipe) o;

					log("RECIPE CLASS,TOSTR,'%s',CN,'%s',CATE,'%s'",
						r,
						r.getClass().getName(),
						RecipeSorter.getCategory(r));

				} else {
					log("Unknown Recipe Class: " + o + " : " + o.getClass().getName());
				}

			}

		}
	}

}
