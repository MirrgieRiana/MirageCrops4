package mirrg.miragecrops4.core;

import java.util.List;

import mirrg.mir40.math.HelpersMath;
import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs p_149666_2_, List list)
	{
		for (int i = 0; i <= 15; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

}
