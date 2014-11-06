package mirrg.miragecrops4.lib.oregen.multi;

import java.util.Random;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.BlockMulti;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockMultiOregen<MULTI extends IMulti<MULTI, META>, META extends IMetablockOregen<MULTI, META>>
	extends BlockMulti<MULTI, META>
{

	public BlockMultiOregen(Material material, MULTI multi)
	{
		super(material, multi);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		getMeta(world, x, y, z).updateTick(world, x, y, z, random);
	}

}
