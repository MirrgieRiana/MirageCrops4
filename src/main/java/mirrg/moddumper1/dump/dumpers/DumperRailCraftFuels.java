package mirrg.moddumper1.dump.dumpers;

import java.util.Map.Entry;

import mirrg.moddumper1.dump.IDumper;
import mirrg.moddumper1.dump.ILogger;
import mirrg.moddumper1.dump.helpers.HelpersDump;
import mirrg.moddumper1.dump.helpers.ICallable1;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

class DumperRailCraftFuels implements IDumper
{

	@Override
	public String getName()
	{
		return "dumperRailCraftFuels";
	}

	@Override
	public void onDump(final ILogger logger)
	{
		logger.log("[FluidFuels]");

		for (Entry<Fluid, Integer> entry : FuelManager.boilerFuel.entrySet()) {

			logger.log("FLUID,LN,'%s',UN,'%s',V,'%s'",
				entry.getKey().getLocalizedName(new FluidStack(entry.getKey(), 1000)),
				entry.getKey().getName(),
				entry.getValue());

		}

		logger.log("[SolidFuels]");

		HelpersDump.eachAllItemStacks(new ICallable1<ItemStack>() {

			@Override
			public void call(ItemStack object)
			{
				int itemBurnTime = TileEntityFurnace.getItemBurnTime(object);

				if (itemBurnTime != 0) {

					logger.log("ITEM,%s,'%s'",
						HelpersDump.getItemStackString(object),
						itemBurnTime);

				}
			}

		});

	}

}
