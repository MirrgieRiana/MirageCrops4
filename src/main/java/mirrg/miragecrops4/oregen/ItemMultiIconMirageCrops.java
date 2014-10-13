package mirrg.miragecrops4.oregen;

import mirrg.mir40.item.ItemMultiIcon;
import mirrg.mir40.item.glob.MetaitemIconGlob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemMultiIconMirageCrops<T extends MetaitemIconGlob> extends ItemMultiIcon<T>
{

	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_)
	{
		if (StatCollector.canTranslate(getUnlocalizedNameInefficiently(p_77653_1_))) {
			return super.getItemStackDisplayName(p_77653_1_);
		}

		String globName = multibase.get(p_77653_1_.getItemDamage()).getGlob().getName();
		String slotName = multibase.get(p_77653_1_.getItemDamage()).getSlot().getName();

		String format = StatCollector.translateToLocal("slot." + slotName + ".format").trim();

		return String.format(format,
			StatCollector.translateToLocal("glob." + globName + ".name").trim());
	}

}
