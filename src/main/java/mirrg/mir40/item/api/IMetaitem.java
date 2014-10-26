package mirrg.mir40.item.api;

import java.util.List;

import mirrg.mir40.multi.api.IMeta;
import mirrg.mir40.multi.api.IMulti;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IMetaitem<MULTI extends IMulti<MULTI, META>, META extends IMetaitem<MULTI, META>>
	extends IMeta<MULTI, META>
{

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs arg1, List<ItemStack> list);

	public IIcon getIcon(ItemStack arg0, int arg1);

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister);

	public String getPlainName(ItemStack itemStack);

	public String getUnlocalizedName(ItemStack itemStack);

	public void setIconName(String iconName);

	public void setUnlocalizedName(String unlocalizedName);

}
