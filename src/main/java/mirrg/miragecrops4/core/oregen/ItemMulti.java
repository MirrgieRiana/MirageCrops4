package mirrg.miragecrops4.core.oregen;

import java.util.List;

import mirrg.mir40.multi.Multibase;
import mirrg.miragecrops4.core.ModuleCore;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMulti<T extends Metaitem> extends Item
{

	public Multibase<T> multibase = new Multibase<T>(256);

	public ItemMulti()
	{
		setCreativeTab(ModuleCore.creativeTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item arg0, CreativeTabs arg1, List arg2)
	{
		for (Metaitem metaitem : multibase) {
			if (metaitem != null) {
				metaitem.getSubItems(arg0, arg1, arg2);
			}
		}
	}

	@Override
	public IIcon getIcon(ItemStack arg0, int arg1)
	{
		int meta = arg0.getItemDamage();
		return multibase.get(meta).getIcon(arg0, arg1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister arg0)
	{
		for (Metaitem metaitem : multibase) {
			if (metaitem != null) {
				metaitem.registerIcons(arg0);
			}
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack arg0)
	{
		int meta = arg0.getItemDamage();
		return multibase.get(meta).getUnlocalizedName(arg0);
	}

}
