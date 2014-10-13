package mirrg.mir40.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMulti<T extends Metablock> extends ItemBlock
{

	protected BlockMulti<T> blockMulti;

	public ItemBlockMulti(Block block)
	{
		super(block);
		blockMulti = (BlockMulti) block;
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return blockMulti.multibase.get(itemStack.getItemDamage()).getUnlocalizedName(itemStack);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

}
