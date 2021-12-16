package mirrg.miragecrops4.fairy.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.Metablock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetablockMirageMachine<MULTI extends IMulti<MULTI, META>, META extends IMetablockMirageMachine<MULTI, META>>
	extends Metablock<MULTI, META> implements IMetablockMirageMachine<MULTI, META>
{

	public Class<? extends TileEntityMirageMachine> classTileEntity = TileEntityMirageMachine.class;

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		TileEntityMirageMachine tileEntity;
		try {
			tileEntity = classTileEntity.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		tileEntity.virtualId = getIndex();
		return tileEntity;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null) return false;

		if (tileEntity instanceof ITileEntityMirageMachine) {
			return ((ITileEntityMirageMachine) tileEntity).onBlockActivated(world, x, y, z, player, side, x2, y2, z2);
		}

		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metaId)
	{
		world.removeTileEntity(x, y, z);
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParameter)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null) return false;
		return tileEntity.receiveClientEvent(eventId, eventParameter);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata)
	{
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!BlockMultiMirageMachine.isInMultipleRendering) return 0xFFFFFF;
		if (BlockMultiMirageMachine.pass == 1) return 0x0022ff;
		return 0xffffff;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int metaId)
	{
		if (!BlockMultiMirageMachine.isInMultipleRendering) return 0xFFFFFF;
		if (BlockMultiMirageMachine.pass == 1) return 0x0022ff;
		return 0xffffff;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		if (!BlockMultiMirageMachine.isInMultipleRendering) return icon;
		if (BlockMultiMirageMachine.pass == 1) return Blocks.potatoes.getIcon(0, 4);
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metaId)
	{
		if (!BlockMultiMirageMachine.isInMultipleRendering) return icon;
		if (BlockMultiMirageMachine.pass == 1) return Blocks.potatoes.getIcon(0, 4);
		return icon;
	}

}
