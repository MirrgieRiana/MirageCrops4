package mirrg.mir40.multi;

import java.util.List;

import mirrg.h.multi.IMulti;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMulti<MULTI extends IMulti<MULTI, META>, META extends IMetablock<MULTI, META>>
	extends Block
{

	public BlockMulti(Material material, MULTI multi)
	{
		super(material);
		this.multi = multi;
	}

	//

	private final MULTI multi;

	public MULTI getMulti()
	{
		return multi;
	}

	public META getMeta(IBlockAccess blockAccess, int x, int y, int z)
	{
		int index = blockAccess.getBlockMetadata(x, y, z);
		return getMeta(index);
	}

	public META getMeta(ItemStack itemStack)
	{
		return getMeta(itemStack.getItemDamage());
	}

	public META getMeta(int index)
	{
		return multi.getMeta(index);
	}

	//

	@Override
	public int damageDropped(int damage)
	{
		return damage;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, @SuppressWarnings("rawtypes") List list)
	{
		for (META meta : multi) {
			if (meta != null) {
				meta.getSubBlocks(item, creativeTabs, list);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return getMeta(blockAccess, x, y, z).getIcon(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metaId)
	{
		return getMeta(metaId).getIcon(side, metaId);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		for (META meta : multi) {
			if (meta != null) {
				meta.registerBlockIcons(iconRegister);
			}
		}
	}

}
