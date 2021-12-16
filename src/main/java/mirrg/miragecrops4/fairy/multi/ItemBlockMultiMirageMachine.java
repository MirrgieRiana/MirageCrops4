package mirrg.miragecrops4.fairy.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.ItemBlockMulti;
import net.minecraft.block.Block;

public class ItemBlockMultiMirageMachine<MULTI extends IMulti<MULTI, META>, META extends IMetablockMirageMachine<MULTI, META>>
	extends ItemBlockMulti<MULTI, META>
{

	public ItemBlockMultiMirageMachine(Block block)
	{
		super(block);
	}

	@Override
	public int getMetadata(int metaId)
	{
		return 0;
	}

}
