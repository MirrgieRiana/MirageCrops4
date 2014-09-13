package mirrg.miragecrops4.core.oregen;

import java.util.List;

import mirrg.mir40.multi.Metabase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Metablock extends Metabase
{

	// ----------------------------- registration -----------------------------

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		list.add(new ItemStack(item, 1, getIndex()));
	}

	// ----------------------------- icon -----------------------------

	@SideOnly(Side.CLIENT)
	public String iconName;
	@SideOnly(Side.CLIENT)
	public IIcon icon;

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return icon;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return icon;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icon = iconRegister.registerIcon(iconName);
	}

	// ----------------------------- unlocalizedName -----------------------------

	public String unlocalizedName;

	public String getPlainName(ItemStack itemStack)
	{
		return unlocalizedName;
	}

	public String getUnlocalizedName(ItemStack itemStack)
	{
		return "tile." + unlocalizedName;
	}

}
