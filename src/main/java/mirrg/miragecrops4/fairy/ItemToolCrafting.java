package mirrg.miragecrops4.fairy;

import java.util.HashSet;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

class ItemToolCrafting extends ItemTool
{

	public ItemToolCrafting()
	{
		super(1, ToolMaterial.IRON, new HashSet());
	}

	@Override
	public boolean hasContainerItem(ItemStack stack)
	{
		return true;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_)
	{
		return false;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack itemStack2 = itemStack.copy();

		itemStack2.setItemDamage(itemStack2.getItemDamage() + 1);

		if (itemStack2.getMaxDamage() < itemStack2.getItemDamage()) {
			return null;
		}

		return itemStack2;
	}
}
