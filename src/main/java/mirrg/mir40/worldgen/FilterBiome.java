package mirrg.mir40.worldgen;

import java.util.Random;

import net.minecraft.world.World;

public class FilterBiome
{

	private String[] biomeNameRequired;

	public FilterBiome(String... biomeNameRequired)
	{
		this.biomeNameRequired = biomeNameRequired;
	}

	public boolean isSpawnableAtCoord(World world, Random random, int x, int y, int z)
	{
		String currentBiomeName = world.getBiomeGenForCoords(x, z).biomeName.toLowerCase();

		for (String biomeName : biomeNameRequired) {
			if (!currentBiomeName.contains(biomeName)) {
				return false;
			}
		}

		return true;
	}

}
