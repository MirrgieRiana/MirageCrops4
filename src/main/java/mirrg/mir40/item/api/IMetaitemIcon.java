package mirrg.mir40.item.api;

import mirrg.mir40.multi.IMulti;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IMetaitemIcon<MULTI extends IMulti<MULTI, META>, META extends IMetaitemIcon<MULTI, META>>
	extends IMetaitem<MULTI, META>
{

	@SideOnly(Side.CLIENT)
	public int getRenderPasses(int metadata);

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass);

	@Override
	public IIcon getIcon(ItemStack arg0, int arg1);

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister);

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int pass);

}
