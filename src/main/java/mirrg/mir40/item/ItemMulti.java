package mirrg.mir40.item;

import java.util.List;

import mirrg.mir40.multi.Multibase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMulti<MULTI extends Multibase<MULTI, META>, META extends Metaitem<MULTI, META>>
	extends Item
{

	public Multibase<MULTI, META> multibase = new Multibase<MULTI, META>(256);

	public ItemMulti()
	{
		setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item arg0, CreativeTabs arg1, List arg2)
	{
		for (Metaitem<MULTI, META> metaitem : multibase) {
			if (metaitem != null) {
				metaitem.getSubItems(arg0, arg1, arg2);
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
		int meta = arg0.getItemDamage();
		return multibase.get(meta).getIcon(arg0, arg1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister arg0)
	{
		for (Metaitem<MULTI, META> metaitem : multibase) {
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
