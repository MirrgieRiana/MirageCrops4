package mirrg.mir40.block;

import mirrg.mir40.block.api.IMetablock;
import mirrg.mir40.multi.api.IMulti;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMulti<MULTI extends IMulti<MULTI, META>, META extends IMetablock<MULTI, META>>
	extends ItemBlock
{

	public BlockMulti<MULTI, META> blockMulti;

	@SuppressWarnings("unchecked")
	public ItemBlockMulti(Block block)
	{
		super(block);
		blockMulti = (BlockMulti<MULTI, META>) block;
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return blockMulti.multi.getMeta(itemStack.getItemDamage()).getUnlocalizedName(itemStack);
	}

	@Override
	public int getMetadata(int metaId)
	{
		return metaId;
	}

}
