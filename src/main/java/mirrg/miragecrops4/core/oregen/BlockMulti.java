package mirrg.miragecrops4.core.oregen;

import java.util.List;

import mirrg.mir40.multi.Multibase;
import mirrg.miragecrops4.core.ModuleCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMulti<T extends Metablock> extends Block
{

	public Multibase<T> multibase = new Multibase<T>(16);

	public BlockMulti()
	{
		super(Material.rock);
		setCreativeTab(ModuleCore.creativeTab);
	}

	@Override
	public int damageDropped(int damage)
	{
		return damage;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (Metablock metablock : multibase) {
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
		for (Metablock metablock : multibase) {
			if (metablock != null) {
				metablock.registerBlockIcons(iconRegister);
			}
		}
	}

}
