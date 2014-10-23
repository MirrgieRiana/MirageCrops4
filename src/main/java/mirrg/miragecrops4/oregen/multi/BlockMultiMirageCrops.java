package mirrg.miragecrops4.oregen.multi;

import java.util.Random;

import mirrg.mir40.block.BlockMulti;
import mirrg.mir40.multi.Multibase;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockMultiMirageCrops<MULTI extends Multibase<MULTI, META>, META extends MetablockGlob<MULTI, META>>
	extends BlockMulti<MULTI, META>
{

	public BlockMultiMirageCrops(Material material)
	{
		super(material);
		setTickRandomly(true);
	}

	@SuppressWarnings("rawtypes")
	public static class Raw extends BlockMultiMirageCrops
	{

		public Raw(Material material)
		{
			super(material);
		}

	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z);
		multibase.get(meta).updateTick(world, x, y, z, random);
	}

}
