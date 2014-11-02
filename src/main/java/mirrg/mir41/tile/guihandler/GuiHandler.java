package mirrg.mir41.tile.guihandler;

import mirrg.mir34.modding.IMod;
import mirrg.mir40.multi.BlockMulti;
import mirrg.mir40.multi.IMetablock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiHandler extends GuiHandlerSuper
{

	public final int guiId;
	public final IMod iMod;

	public GuiHandler(int guiId, IMod iMod)
	{
		this.guiId = guiId;
		this.iMod = iMod;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == guiId) {
			Block block = world.getBlock(x, y, z);

			if (block instanceof IGuiProvider) {
				return ((IGuiProvider) block).createGui(player, world, x, y, z);
			}

			if (block instanceof BlockMulti) {
				IMetablock<?, ?> meta = ((BlockMulti<?, ?>) block).multi.getMeta(world.getBlockMetadata(x, y, z));

				if (meta instanceof IGuiProvider) {
					return ((IGuiProvider) meta).createGui(player, world, x, y, z);
				}
			}

			{
				TileEntity tile = world.getTileEntity(x, y, z);

				if (tile != null && tile instanceof IGuiProvider) {
					return ((IGuiProvider) tile).createGui(player, world, x, y, z);
				}
			}
		}

		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == guiId) {
			Block block = world.getBlock(x, y, z);

			if (block instanceof IGuiProvider) {
				return ((IGuiProvider) block).createContainer(player, world, x, y, z);
			}

			if (block instanceof BlockMulti) {
				IMetablock<?, ?> meta = ((BlockMulti<?, ?>) block).multi.getMeta(world.getBlockMetadata(x, y, z));

				if (meta instanceof IGuiProvider) {
					return ((IGuiProvider) meta).createContainer(player, world, x, y, z);
				}
			}

			{
				TileEntity tile = world.getTileEntity(x, y, z);

				if (tile != null && tile instanceof IGuiProvider) {
					return ((IGuiProvider) tile).createContainer(player, world, x, y, z);
				}
			}
		}

		return null;
	}

}

abstract class GuiHandlerSuper implements IGuiHandler
{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

}
