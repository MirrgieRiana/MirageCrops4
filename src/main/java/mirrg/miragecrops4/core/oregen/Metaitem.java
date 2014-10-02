package mirrg.miragecrops4.core.oregen;

import java.util.List;

import mirrg.mir40.multi.Metabase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Metaitem extends Metabase
{

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs arg1, List list)
	{
		list.add(new ItemStack(item, 1, getIndex()));
	}

	// ----------------------------- icon -----------------------------

	@SideOnly(Side.CLIENT)
	public String iconName;
	@SideOnly(Side.CLIENT)
	public IIcon icon;

	public IIcon getIcon(ItemStack arg0, int arg1)
	{
		return icon;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
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
		return "item." + unlocalizedName;
	}

}