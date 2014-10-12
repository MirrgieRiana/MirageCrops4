package mirrg.moddumper1.core;

import java.util.Map.Entry;

import mirrg.mir34.modding.IMod;
import mirrg.moddumper1.ICallable1;
import mirrg.moddumper1.ModuleDumperAbstract;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.FMLLog;

public class ModuleDumperRailCraftFuels extends ModuleDumperAbstract
{

	public ModuleDumperRailCraftFuels(IMod mod)
	{
		super(mod);
	}

	@Override
	public String getModuleName()
	{
		return "dumperRecipeSorter";
	}

	@Override
	protected void onDump()
	{
		FMLLog.info("[RailCraftBoilerFuels] FluidFuels");

		for (Entry<Fluid, Integer> entry : FuelManager.boilerFuel.entrySet()) {

			FMLLog.info("FLUID,LN,'%s',UN,'%s',V,'%s'",
				entry.getKey().getLocalizedName(new FluidStack(entry.getKey(), 1000)),
				entry.getKey().getName(),
				entry.getValue());

		}

		FMLLog.info("[RailCraftBoilerFuels] SolidFuels");

		eachAllItemStacks(new ICallable1<ItemStack>() {

			@Override
			public void call(ItemStack object)
			{
				int itemBurnTime = TileEntityFurnace.getItemBurnTime(object);

				if (itemBurnTime != 0) {

					FMLLog.info("ITEM,%s,'%s'",
						getItemStackString(object),
						itemBurnTime);

				}
			}

		});

	}

}
