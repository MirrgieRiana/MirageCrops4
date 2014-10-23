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
		if (world.rand.nextInt(1) == 0)
		{
			tryCorroding(world, x + 1, y, z, random);
			tryCorroding(world, x - 1, y, z, random);
			tryCorroding(world, x, y + 1, z, random);
			tryCorroding(world, x, y - 1, z, random);
			tryCorroding(world, x, y, z + 1, random);
			tryCorroding(world, x, y, z - 1, random);
		}
	}

	protected void tryCorroding(World world, int x, int y, int z, Random random)
	{
		int t = 0;
		t += isOreSpinatite(world, x + 1, y, z) ? 1 : 0;
		t += isOreSpinatite(world, x - 1, y, z) ? 1 : 0;
		t += isOreSpinatite(world, x, y + 1, z) ? 1 : 0;
		t += isOreSpinatite(world, x, y - 1, z) ? 1 : 0;
		t += isOreSpinatite(world, x, y, z + 1) ? 1 : 0;
		t += isOreSpinatite(world, x, y, z - 1) ? 1 : 0;
		if (t >= 2) {
			putOreSpinatite(world, x, y, z);
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
