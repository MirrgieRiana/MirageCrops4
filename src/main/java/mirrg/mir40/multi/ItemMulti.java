package mirrg.mir40.multi;

import java.util.List;

import mirrg.h.multi.IMulti;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMulti<MULTI extends IMulti<MULTI, META>, META extends IMetaitem<MULTI, META>>
	extends Item
{

	public final MULTI multi;

	public ItemMulti(MULTI multi)
	{
		this.multi = multi;
		setHasSubtypes(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item arg0, CreativeTabs arg1, @SuppressWarnings("rawtypes") List arg2)
	{
		for (META meta : multi) {
			if (meta != null) {
				meta.getSubItems(arg0, arg1, arg2);
			}
		}
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
		return 1;
	}

	@Override
	public IIcon getIcon(ItemStack arg0, int arg1)
	{
		int metaId = arg0.getItemDamage();
		return multi.getMeta(metaId).getIcon(arg0, arg1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister arg0)
	{
		for (META meta : multi) {
			if (meta != null) {
				meta.registerIcons(arg0);
			}
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack arg0)
	{
		int metaId = arg0.getItemDamage();
		return multi.getMeta(metaId).getUnlocalizedName(arg0);
	}

}
