package mirrg.mir40.glob;

import java.util.Hashtable;

import mirrg.mir40.glob.api.IGlob;
import mirrg.mir40.glob.api.ISlot;
import net.minecraft.item.ItemStack;

public class GlobAbstract implements IGlob
{

	protected String name;

	public GlobAbstract()
	{

	}

	public GlobAbstract(String name)
	{
		setName(name);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	protected Hashtable<String, ItemStack> table = new Hashtable<String, ItemStack>();

	@Override
	public boolean contains(ISlot slot)
	{
		return table.containsKey(slot.getName());
	}

	@Override
	public ItemStack copy(ISlot slot)
	{
		return table.get(slot.getName());
	}

	public void put(ISlot slot, ItemStack itemStack)
	{
		put(slot.getName(), itemStack.copy());
	}

	public void put(String name, ItemStack itemStack)
	{
		table.put(name, itemStack.copy());
	}

}
