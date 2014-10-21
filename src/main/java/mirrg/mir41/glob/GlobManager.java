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

	private Map<String, ItemStack> addItems = new Hashtable<String, ItemStack>();

	@Override
	public Map<String, ItemStack> getAllItems()
	{
		return addItems;
	}

	@Override
	public ItemStack get(String dictionaryName)
	{
		return addItems.get(dictionaryName);
	}

	@Override
	public boolean contains(String dictionaryName)
	{
		return addItems.containsKey(dictionaryName);
	}

	@Override
	public boolean contains(ItemStack itemStack)
	{
		return addItems.containsValue(itemStack);
	}

	@Override
	public String getDictionaryName(SLOT slot, GLOB glob)
	{
		return slot.getName() + HelpersString.toUpperCaseHead(glob.getName());
	}

}
