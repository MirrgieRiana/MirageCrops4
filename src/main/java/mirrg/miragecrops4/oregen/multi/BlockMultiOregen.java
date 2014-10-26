package mirrg.miragecrops4.oregen.multi;

import java.util.Random;

import mirrg.mir40.block.BlockMulti;
import mirrg.mir40.multi.api.IMulti;
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
		int metaId = world.getBlockMetadata(x, y, z);
		multi.getMeta(metaId).updateTick(world, x, y, z, random);
	}

}
