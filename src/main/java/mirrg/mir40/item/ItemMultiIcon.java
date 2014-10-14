package mirrg.mir40.item;

import mirrg.mir40.multi.Multibase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMultiIcon<MULTI extends Multibase<MULTI, META>, META extends MetaitemIcon<MULTI, META>>
	extends ItemMulti<MULTI, META>
{

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderPasses(int metadata)
	{
		return multibase.get(metadata).getRenderPasses(metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return multibase.get(meta).getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int pass)
	{
		int meta = par1ItemStack.getItemDamage();
		return multibase.get(meta).getColorFromItemStack(par1ItemStack, pass);
	}

}
