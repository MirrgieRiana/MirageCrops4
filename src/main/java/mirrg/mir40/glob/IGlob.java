package mirrg.mir40.glob;

import net.minecraft.item.ItemStack;

public interface IGlob
{

	public String getName();

	public boolean contains(ISlot slot);

	public ItemStack copy(ISlot slot);

}
