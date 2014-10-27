package mirrg.miragecrops4.lib.oregen.multi;

import mirrg.h.multi.IMulti;
import mirrg.mir40.multi.ItemMultiIcon;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemMultiIconOregen<MULTI extends IMulti<MULTI, META>, META extends IMetaitemIconOregen<MULTI, META>>
	extends ItemMultiIcon<MULTI, META>
{

	public ItemMultiIconOregen(MULTI multi)
	{
		super(multi);
	}

	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_)
	{
		if (StatCollector.canTranslate(getUnlocalizedNameInefficiently(p_77653_1_))) {
			return super.getItemStackDisplayName(p_77653_1_);
		}

		String globName = multi.getMeta(p_77653_1_.getItemDamage()).getGlob().getName();
		String slotName = multi.getMeta(p_77653_1_.getItemDamage()).getSlot().getName();

		String format = StatCollector.translateToLocal("slot." + slotName + ".format").trim();

		return String.format(format,
			StatCollector.translateToLocal("glob." + globName + ".name").trim());
	}

}
