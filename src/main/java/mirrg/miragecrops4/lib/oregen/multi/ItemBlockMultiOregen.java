package mirrg.miragecrops4.lib.oregen.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.ItemBlockMulti;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockMultiOregen<MULTI extends IMulti<MULTI, META>, META extends IMetablockOregen<MULTI, META>>
	extends ItemBlockMulti<MULTI, META>
{

	public ItemBlockMultiOregen(Block block)
	{
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_)
	{
		if (StatCollector.canTranslate(getUnlocalizedNameInefficiently(p_77653_1_))) {
			return super.getItemStackDisplayName(p_77653_1_);
		}

		String globName = blockMulti.getMeta(p_77653_1_).getGlob().getName();
		String slotName = blockMulti.getMeta(p_77653_1_).getSlot().getName();

		String format = StatCollector.translateToLocal("slot." + slotName + ".format").trim();

		return String.format(format,
			StatCollector.translateToLocal("glob." + globName + ".name").trim());
	}

}
