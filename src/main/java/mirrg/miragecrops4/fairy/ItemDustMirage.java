package mirrg.miragecrops4.fairy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDustMirage extends Item
{

	public ItemStack createChangedItem()
	{
		return new ItemStack(Items.potionitem);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
	{
		--p_77659_1_.stackSize;

		p_77659_2_.playSoundEffect(
			(double) ((float) p_77659_3_.posX + 0.5F),
			(double) ((float) p_77659_3_.posY + 0.5F),
			(double) ((float) p_77659_3_.posZ + 0.5F),
			"random.fizz",
			0.5F, 2.6F + (p_77659_2_.rand.nextFloat() - p_77659_2_.rand.nextFloat()) * 0.8F);

		if (p_77659_1_.stackSize <= 0)
		{
			return createChangedItem();
		}

		if (!p_77659_3_.inventory.addItemStackToInventory(createChangedItem()))
		{
			p_77659_3_.dropPlayerItemWithRandomChoice(createChangedItem(), false);
		}

		return p_77659_1_;
	}

}
