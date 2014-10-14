package mirrg.mir40.block;

import mirrg.mir40.multi.Multibase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMulti<MULTI extends Multibase<MULTI, META>, META extends Metablock<MULTI, META>>
	extends ItemBlock
{

	protected BlockMulti<MULTI, META> blockMulti;

	public ItemBlockMulti(Block block)
	{
		super(block);
		blockMulti = (BlockMulti<MULTI, META>) block;
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
