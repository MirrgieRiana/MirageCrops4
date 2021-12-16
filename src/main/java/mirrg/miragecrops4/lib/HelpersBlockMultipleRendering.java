package mirrg.miragecrops4.lib;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HelpersBlockMultipleRendering
{

	@SideOnly(Side.CLIENT)
	public static <T extends Block & IBlockMultipleRendering>
		void setMultipleRendering(T block, boolean enabled)
	{
		block.setMultipleRendering(enabled);
	}

	@SideOnly(Side.CLIENT)
	public static <T extends Block & IBlockMultipleRendering>
		int getMultipleRenderPasses(T block, IBlockAccess blockAccess, int x, int y, int z)
	{
		return block.getMultipleRenderPasses(blockAccess, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public static <T extends Block & IBlockMultipleRendering>
		int getMultipleRenderColor(T block, IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		block.setMultipleRenderPass(pass);
		return block.colorMultiplier(blockAccess, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public static <T extends Block & IBlockMultipleRendering>
		IIcon getMultipleRenderIcon(T block, IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		block.setMultipleRenderPass(pass);
		return block.getIcon(blockAccess, x, y, z, side);
	}

	@SideOnly(Side.CLIENT)
	public static <T extends Block & IBlockMultipleRendering>
		int getMultipleRenderPasses(T block, int metadata)
	{
		return block.getMultipleRenderPasses(metadata);
	}

	@SideOnly(Side.CLIENT)
	public static <T extends Block & IBlockMultipleRendering>
		int getMultipleRenderColor(T block, int metadata, int pass)
	{
		block.setMultipleRenderPass(pass);
		return block.getRenderColor(metadata);
	}

	@SideOnly(Side.CLIENT)
	public static <T extends Block & IBlockMultipleRendering>
		IIcon getMultipleRenderIcon(T block, int side, int metadata, int pass)
	{
		block.setMultipleRenderPass(pass);
		return block.getIcon(side, metadata);
	}

}
