package mirrg.miragecrops4.core.oregen;

import mirrg.mir40.icon.MultiIcon;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetaitemIcon extends Metaitem
{

	protected MultiIcon multiIcon;

	@SideOnly(Side.CLIENT)
	public int getRenderPasses(int metadata)
	{
		return multiIcon.getLength();
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return multiIcon.getIcon(pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		multiIcon.register(par1IconRegister);
		return;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int pass)
	{
		return multiIcon.getColor(pass);
	}

}
