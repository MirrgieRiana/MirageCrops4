package mirrg.mir41.glob;

import java.util.Hashtable;
import java.util.Map;

import mirrg.mir40.math.HelpersString;
import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.IGlobManager;
import mirrg.mir41.glob.api.ISlot;
import net.minecraft.item.ItemStack;

public class GlobManager<SLOT extends ISlot, GLOB extends IGlob>
	implements IGlobManager<SLOT, GLOB>
{

	private Map<String, ItemStack> allItems = new Hashtable<String, ItemStack>();

	@Override
	public Map<String, ItemStack> getAllItems()
	{
		return allItems;
	}

	@Override
	public ItemStack get(String dictionaryName)
	{
		return allItems.get(dictionaryName);
	}

	@Override
	public boolean contains(String dictionaryName)
	{
		return allItems.containsKey(dictionaryName);
	}

	@Override
	public boolean contains(ItemStack itemStack)
	{
		return allItems.containsValue(itemStack);
	}

	public void put(String dictionaryName, ItemStack itemStack)
	{
		allItems.put(dictionaryName, itemStack);
	}

	@Override
	public String getDictionaryName(SLOT slot, GLOB glob)
	{
		return slot.getName() + HelpersString.toUpperCaseHead(glob.getName());
	}

}
