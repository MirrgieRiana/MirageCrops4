package mirrg.miragecrops4.core;

import mirrg.mir40.math.HelpersMath;
import net.minecraft.block.BlockTorch;
import net.minecraft.world.IBlockAccess;

public class BlockTorchFeeble extends BlockTorch
{

	public BlockTorchFeeble()
	{

	}

	@Override
	public int getLightValue(IBlockAccess arg0, int arg1, int arg2, int arg3)
	{
		return HelpersMath.trim(arg0.getBlockMetadata(arg1, arg2, arg3), 0, 15);
	}

	@Override
	public int damageDropped(int p_149692_1_)
	{
		return p_149692_1_;
	}

}
