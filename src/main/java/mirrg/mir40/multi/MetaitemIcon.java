package mirrg.mir40.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.icon.MultiIcon;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetaitemIcon<MULTI extends IMulti<MULTI, META>, META extends IMetaitemIcon<MULTI, META>>
	extends Metaitem<MULTI, META> implements IMetaitemIcon<MULTI, META>
{

	public MultiIcon multiIcon;

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderPasses(int metadata)
	{
		return multiIcon.getLength();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return multiIcon.getIcon(pass);
	}

	@Override
	public IIcon getIcon(ItemStack arg0, int arg1)
	{
		return getIconFromDamageForRenderPass(arg0.getItemDamage(), arg1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		multiIcon.register(par1IconRegister);
		return;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int pass)
	{
		return multiIcon.getColor(pass);
	}

}
