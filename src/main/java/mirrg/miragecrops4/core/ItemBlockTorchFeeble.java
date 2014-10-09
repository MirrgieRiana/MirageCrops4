package mirrg.miragecrops4.core;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

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

}
