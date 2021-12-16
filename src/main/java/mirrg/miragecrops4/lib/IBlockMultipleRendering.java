package mirrg.miragecrops4.lib;

import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IBlockMultipleRendering
{

	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(boolean isInMultipleRendering);
	
	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(int pass);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata);

}
