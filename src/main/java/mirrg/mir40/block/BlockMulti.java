package mirrg.mir40.block;

import java.util.List;

import mirrg.mir40.multi.Multibase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMulti<MULTI extends Multibase<MULTI, META>, META extends Metablock<MULTI, META>>
	extends Block
{

	public Multibase<MULTI, META> multibase = new Multibase<MULTI, META>(16);

	public BlockMulti()
	{
		super(Material.rock);
	}

	@Override
	public int damageDropped(int damage)
	{
		return damage;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, @SuppressWarnings("rawtypes") List list)
	{
		for (Metablock<MULTI, META> metablock : multibase) {
			if (metablock != null) {
				metablock.getSubBlocks(item, creativeTabs, list);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		int meta = blockAccess.getBlockMetadata(x, y, z);
		return multibase.get(meta).getIcon(blockAccess, x, y, z, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return multibase.get(meta).getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		for (Metablock<MULTI, META> metablock : multibase) {
			if (metablock != null) {
				metablock.registerBlockIcons(iconRegister);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static class Raw extends BlockMulti
	{

	}

}
