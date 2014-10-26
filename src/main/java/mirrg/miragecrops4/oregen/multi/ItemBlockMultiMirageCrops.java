package mirrg.miragecrops4.oregen.multi;

import mirrg.mir40.block.ItemBlockMulti;
import mirrg.mir40.multi.api.IMulti;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockMultiMirageCrops<MULTI extends IMulti<MULTI, META>, META extends IMetablockOregen<MULTI, META>>
	extends ItemBlockMulti<MULTI, META>
{

	public ItemBlockMultiMirageCrops(Block block)
	{
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_)
	{
		if (StatCollector.canTranslate(getUnlocalizedNameInefficiently(p_77653_1_))) {
			return super.getItemStackDisplayName(p_77653_1_);
		}

		String globName = blockMulti.multi.getMeta(p_77653_1_.getItemDamage()).getGlob().getName();
		String slotName = blockMulti.multi.getMeta(p_77653_1_.getItemDamage()).getSlot().getName();

		String format = StatCollector.translateToLocal("slot." + slotName + ".format").trim();

		return String.format(format,
			StatCollector.translateToLocal("glob." + globName + ".name").trim());
	}

}
