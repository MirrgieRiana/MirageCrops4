package mirrg.miragecrops4.fairy.multi;

import java.util.ArrayList;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.BlockMulti;
import mirrg.miragecrops4.fairy.ModuleFairy;
import mirrg.miragecrops4.lib.IBlockMultipleRendering;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 常にmetadataが0、TileEntityにバーチャルIDを持つ
 */
public class BlockMultiMirageMachine<MULTI extends IMulti<MULTI, META>, META extends IMetablockMirageMachine<MULTI, META>>
	extends BlockMulti<MULTI, META> implements ITileEntityProvider, IBlockMultipleRendering
{

	public BlockMultiMirageMachine(Material material, MULTI multi)
	{
		super(material, multi);
	}

	@Override
	public META getMeta(IBlockAccess blockAccess, int x, int y, int z)
	{
		return getMeta(HelpersMultiMirageMachine.getVirtualId(blockAccess, x, y, z));
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metaId)
	{
		getMeta(world, x, y, z).breakBlock(world, x, y, z, block, metaId);
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParameter)
	{
		return getMeta(world, x, y, z).onBlockEventReceived(world, x, y, z, eventId, eventParameter);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
		EntityPlayer player, int side, float x2, float y2, float z2)
	{
		return getMeta(world, x, y, z).onBlockActivated(world, x, y, z, player, side, x2, y2, z2);
	}

	@Override
	public boolean hasTileEntity(int metaId)
	{
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaId)
	{
		return null;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase arg4, ItemStack arg5)
	{
		world.setTileEntity(x, y, z, getMeta(arg5).createNewTileEntity(world));
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return HelpersMultiMirageMachine.getVirtualId(world, x, y, z);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<>();

		int count = quantityDropped(metadata, fortune, world.rand);
		for (int i = 0; i < count; ++i)
		{
			Item item = getItemDropped(metadata, world.rand, fortune);
			if (item == null)
				continue;
			ret.add(new ItemStack(item, 1, getDamageValue(world, x, y, z)));
		}

		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return ModuleFairy.renderBlockMirageMachine.getRenderId();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		return getMeta(blockAccess, x, y, z).colorMultiplier(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int metaId)
	{
		return getMeta(metaId).getRenderColor(metaId);
	}

	@SideOnly(Side.CLIENT)
	public static int pass;

	@SideOnly(Side.CLIENT)
	public static boolean isInMultipleRendering;

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(boolean isInMultipleRendering)
	{
		this.isInMultipleRendering = isInMultipleRendering;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(int pass)
	{
		this.pass = pass;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		return getMeta(blockAccess, x, y, z).getMultipleRenderPasses(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata)
	{
		return getMeta(metadata).getMultipleRenderPasses(metadata);
	}

}
