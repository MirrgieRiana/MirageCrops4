package mirrg.miragecrops4.fairy.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.IMetablock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IMetablockMirageMachine<MULTI extends IMulti<MULTI, META>, META extends IMetablockMirageMachine<MULTI, META>>
	extends IMetablock<MULTI, META>
{

	public TileEntity createNewTileEntity(World world);

	public boolean onBlockActivated(World world, int x, int y, int z,
		EntityPlayer player, int side, float x2, float y2, float z2);

	public void breakBlock(World world, int x, int y, int z, Block block, int metaId);

	public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParameter);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata);

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z);

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int metaId);

}
