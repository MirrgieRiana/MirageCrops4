package mirrg.miragecrops4.lib.oregen.multi;

import java.util.List;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.MetaitemIcon;
import mirrg.mir41.glob.IGlob;
import mirrg.mir41.glob.ISlot;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetaitemIconOregen<MULTI extends IMulti<MULTI, META>, META extends IMetaitemIconOregen<MULTI, META>>
	extends MetaitemIcon<MULTI, META> implements IMetaitemIconOregen<MULTI, META>
{

	protected IGlob glob;
	protected ISlot slot;

	public boolean isAllowed = false;

	public MetaitemIconOregen(IGlob glob, ISlot slot)
	{
		this.glob = glob;
		this.slot = slot;
	}

	@Override
	public IGlob getGlob()
	{
		return glob;
	}

	@Override
	public ISlot getSlot()
	{
		return slot;
	}

	@Override
	public void setGlob(IGlob glob)
	{
		this.glob = glob;
	}

	@Override
	public void setSlot(ISlot slot)
	{
		this.slot = slot;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs arg1, List<ItemStack> list)
	{
		if (isAllowed) super.getSubItems(item, arg1, list);
	}

}
