package mirrg.mir41.glob.api;

import java.util.Map;

import net.minecraft.item.ItemStack;

public interface IGlobManager<SLOT extends ISlot, GLOB extends IGlob>
{

	public Map<String, ItemStack> getAllItems();

	public ItemStack get(String dictionaryName);

	public boolean contains(String dictionaryName);

	public boolean contains(ItemStack itemStack);

	public String getDictionaryName(SLOT slot, GLOB glob);

}
