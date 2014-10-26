package mirrg.miragecrops4.core.torch;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockTorchFeeble extends ItemBlock
{

	public ItemBlockTorchFeeble(Block arg0)
	{
		super(arg0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int p_77647_1_)
	{
		return p_77647_1_;
	}

	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_)
	{
		return super.getItemStackDisplayName(p_77653_1_) + " - " + p_77653_1_.getItemDamage();
	}

}
