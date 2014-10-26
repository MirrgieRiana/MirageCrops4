package mirrg.mir40.block;

import java.util.List;

import mirrg.h.multi.IMulti;
import mirrg.h.multi.Meta;
import mirrg.mir40.block.api.IMetablock;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Metablock<MULTI extends IMulti<MULTI, META>, META extends IMetablock<MULTI, META>>
	extends Meta<MULTI, META> implements IMetablock<MULTI, META>
{

	// ----------------------------- registration -----------------------------

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> list)
	{
		list.add(new ItemStack(item, 1, getIndex()));
	}

	// ----------------------------- icon -----------------------------

	@SideOnly(Side.CLIENT)
	public String iconName;
	@SideOnly(Side.CLIENT)
	public IIcon icon;

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icon = iconRegister.registerIcon(iconName);
	}

	// ----------------------------- unlocalizedName -----------------------------

	public String unlocalizedName;

	@Override
	public String getPlainName(ItemStack itemStack)
	{
		return unlocalizedName;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return "tile." + unlocalizedName;
	}

	@Override
	public void setIconName(String iconName)
	{
		this.iconName = iconName;
	}

	@Override
	public void setUnlocalizedName(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
	}

}
