package mirrg.miragecrops4.oregen.multi;

import mirrg.mir40.block.ItemBlockMulti;
import mirrg.mir40.multi.Multibase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockMultiMirageCrops<MULTI extends Multibase<MULTI, META>, META extends MetablockGlob<MULTI, META>>
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

		String globName = blockMulti.multibase.get(p_77653_1_.getItemDamage()).getGlob().getName();
		String slotName = blockMulti.multibase.get(p_77653_1_.getItemDamage()).getSlot().getName();

		String format = StatCollector.translateToLocal("slot." + slotName + ".format").trim();

		return String.format(format,
			StatCollector.translateToLocal("glob." + globName + ".name").trim());
	}

}
