package mirrg.mir40.item;

import java.util.List;

import mirrg.mir40.item.api.IMetaitem;
import mirrg.mir40.multi.Metabase;
import mirrg.mir40.multi.api.IMulti;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Metaitem<MULTI extends IMulti<MULTI, META>, META extends IMetaitem<MULTI, META>>
	extends Metabase<MULTI, META> implements IMetaitem<MULTI, META>
{

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs arg1, List<ItemStack> list)
	{
		list.add(new ItemStack(item, 1, getIndex()));
	}

	// ----------------------------- icon -----------------------------

	@SideOnly(Side.CLIENT)
	public String iconName;
	@SideOnly(Side.CLIENT)
	public IIcon icon;

	@Override
	public IIcon getIcon(ItemStack arg0, int arg1)
	{
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
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
		return "item." + unlocalizedName;
	}

}
