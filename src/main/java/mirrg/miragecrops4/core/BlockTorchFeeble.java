package mirrg.miragecrops4.core;

import java.util.List;
import java.util.Random;

import mirrg.mir40.math.HelpersMath;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTorchFeeble extends Block
{

	public BlockTorchFeeble()
	{
		super(Material.circuits);
	}

	@Override
	public int damageDropped(int p_149692_1_)
	{
		return p_149692_1_;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs p_149666_2_, @SuppressWarnings("rawtypes") List list)
	{
		for (int i = 0; i <= 15; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int getLightValue(IBlockAccess arg0, int arg1, int arg2, int arg3)
	{
		return HelpersMath.trim(arg0.getBlockMetadata(arg1, arg2, arg3), 0, 15);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return ModuleCore.renderBlockTorchFeeble.getRenderId();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 p_149731_5_, Vec3 p_149731_6_)
	{
		float f = 0.1F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);

		return super.collisionRayTrace(world, x, y, z, p_149731_5_, p_149731_6_);
	}

	@SideOnly(Side.CLIENT)
	public IIcon overridedIcon;

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		if (overridedIcon != null) return overridedIcon;
		return super.getIcon(p_149691_1_, p_149691_2_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if (overridedIcon != null) return overridedIcon;
		return super.getIcon(world, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random p_149734_5_)
	{
		double d0 = x + 0.5F;
		double d1 = y + 0.7F;
		double d2 = z + 0.5F;

		world.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);

		if (!world.isSideSolid(x, y - 1, z, ForgeDirection.UP, false)) {
			if (world.rand.nextInt(10) == 0) {
				world.spawnParticle("fireworksSpark", d0, y, d2, 0.0D, 0.0D, 0.0D);
			}
		}

	}

}
