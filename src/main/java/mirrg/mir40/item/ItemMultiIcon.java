package mirrg.mir40.item;

import mirrg.mir40.item.api.IMetaitemIcon;
import mirrg.mir40.multi.IMulti;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMultiIcon<MULTI extends IMulti<MULTI, META>, META extends IMetaitemIcon<MULTI, META>>
	extends ItemMulti<MULTI, META>
{

	public ItemMultiIcon(MULTI multi)
	{
		super(multi);
	}

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
		return multi.getMeta(metadata).getRenderPasses(metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int metaId, int pass)
	{
		return multi.getMeta(metaId).getIconFromDamageForRenderPass(metaId, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		int meta = itemStack.getItemDamage();
		return multi.getMeta(meta).getColorFromItemStack(itemStack, pass);
	}

}
