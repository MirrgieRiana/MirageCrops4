package mirrg.mir40.block.api;

import java.util.List;

import mirrg.mir40.multi.api.IMeta;
import mirrg.mir40.multi.api.IMulti;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IMetablock<MULTI extends IMulti<MULTI, META>, META extends IMetablock<MULTI, META>>
	extends IMeta<MULTI, META>
{

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> list);

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side);

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta);

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister);

	public String getPlainName(ItemStack itemStack);

	public String getUnlocalizedName(ItemStack itemStack);

	public void setIconName(String iconName);

	public void setUnlocalizedName(String unlocalizedName);

}
