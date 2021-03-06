package mirrg.miragecrops4.lib.oregen.multi;

import java.util.List;
import java.util.Random;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.Metablock;
import mirrg.mir41.glob.IGlob;
import mirrg.mir41.glob.ISlot;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetablockOregen<MULTI extends IMulti<MULTI, META>, META extends IMetablockOregen<MULTI, META>>
	extends Metablock<MULTI, META> implements IMetablockOregen<MULTI, META>
{

	protected IGlob glob;
	protected ISlot slot;

	public boolean isAllowed = false;

	public MetablockOregen(IGlob glob, ISlot slot)
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
	public void updateTick(World world, int x, int y, int z, Random random)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> list)
	{
		if (isAllowed) super.getSubBlocks(item, creativeTabs, list);
	}

}
