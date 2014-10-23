package mirrg.miragecrops4.fairy;

import java.util.Random;

import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.ISlot;
import mirrg.miragecrops4.oregen.global.ItemsOregen;
import mirrg.miragecrops4.oregen.multi.MetablockGlob;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MetablockOreSpinatite extends MetablockGlob.Raw
{
	public MetablockOreSpinatite(IGlob glob, ISlot slot)
	{
		super(glob, slot);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		int rand = world.rand.nextInt(300);

		if (rand == 0) tryCorroding(world, x + 1, y, z, random);
		else if (rand == 1) tryCorroding(world, x - 1, y, z, random);
		else if (rand == 2) tryCorroding(world, x, y + 1, z, random);
		else if (rand == 3) tryCorroding(world, x, y - 1, z, random);
		else if (rand == 4) tryCorroding(world, x, y, z + 1, random);
		else if (rand == 5) tryCorroding(world, x, y, z - 1, random);
	}

	protected final static int airReach = 5;

	protected void tryCorroding(World world, int x, int y, int z, Random random)
	{
		for (int i = 0; i < 10; i++) {
			int xi = world.rand.nextInt(1 + airReach * 2) - airReach;
			int yi = world.rand.nextInt(1 + airReach * 2) - airReach;
			int zi = world.rand.nextInt(1 + airReach * 2) - airReach;

			if (isOreSpinatite(world, x + xi, y + yi, z + zi)) return;
		}

		int t = 0;
		t += isOreSpinatite(world, x + 1, y, z) ? 1 : 0;
		t += isOreSpinatite(world, x - 1, y, z) ? 1 : 0;
		t += isOreSpinatite(world, x, y + 1, z) ? 1 : 0;
		t += isOreSpinatite(world, x, y - 1, z) ? 1 : 0;
		t += isOreSpinatite(world, x, y, z + 1) ? 1 : 0;
		t += isOreSpinatite(world, x, y, z - 1) ? 1 : 0;
		if (t >= 2) {
			int m = 0;
			m += world.isAirBlock(x + 1, y, z) ? 1 : 0;
			m += world.isAirBlock(x - 1, y, z) ? 1 : 0;
			m += world.isAirBlock(x, y + 1, z) ? 1 : 0;
			m += world.isAirBlock(x, y - 1, z) ? 1 : 0;
			m += world.isAirBlock(x, y, z + 1) ? 1 : 0;
			m += world.isAirBlock(x, y, z - 1) ? 1 : 0;

			if (m >= 1) {
				putOreSpinatite(world, x, y, z);
			}
		}
	}

	protected boolean isCorrodable(World world, int x, int y, int z)
	{
		return world.getBlock(x, y, z) == Blocks.stone;
	}

	protected boolean isOreSpinatite(World world, int x, int y, int z)
	{
		return (world.getBlock(x, y, z) == ItemsOregen.blockOreMirageMagic) &&
			world.getBlockMetadata(x, y, z) ==
			ItemsOregen.EnumGlobGroup.MirageMagic.globGroup.getGlobs().indexOf(ItemsOregen.EnumGlob.spinatite.glob);
	}

	protected void putOreSpinatite(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, ItemsOregen.blockOreMirageMagic,
			ItemsOregen.EnumGlobGroup.MirageMagic.globGroup.getGlobs().indexOf(ItemsOregen.EnumGlob.spinatite.glob), 3);
	}

}
