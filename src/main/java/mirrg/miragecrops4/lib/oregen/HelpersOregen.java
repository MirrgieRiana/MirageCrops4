package mirrg.miragecrops4.lib.oregen;

import mirrg.mir40.math.HelpersString;
import mirrg.mir40.reflect.HelpersReflect;
import mirrg.mir41.glob.IGlobGroup;
import mirrg.mir41.glob.ISlot;
import mirrg.miragecrops4.lib.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.multi.ItemMultiIconOregen;
import mirrg.miragecrops4.lib.multi.MetablockOregen;
import mirrg.miragecrops4.lib.multi.MetaitemIconOregen;
import mirrg.miragecrops4.lib.oregen.GlobsOregen.EnumGlob;
import mirrg.miragecrops4.lib.oregen.GlobsOregen.EnumGlobGroup;
import mirrg.miragecrops4.lib.oregen.GlobsOregen.EnumSlot;

public class HelpersOregen
{

	public static String getBlockUnlocalizedName(ISlot slot, IGlobGroup<?> globGroup)
	{
		if (slot.equals(GlobsOregen.EnumSlot.block.slot)) {
			return "block" + globGroup.getName();
		} else {
			return "block" + HelpersString.toUpperCaseHead(slot.getName()) + globGroup.getName();
		}
	}

	public static String getItemUnlocalizedName(ISlot slot)
	{
		return "item" + HelpersString.toUpperCaseHead(slot.getName());
	}

	public static BlockMultiOregen getBlock(ISlot slot, IGlobGroup<?> globGroup)
	{
		Object blockMulti = HelpersReflect.getStaticField(ItemsOregen.class, getBlockUnlocalizedName(slot, globGroup));
		if (blockMulti == null || blockMulti instanceof Exception) {
			throw new RuntimeException((Exception) blockMulti);
		}

		return (BlockMultiOregen) blockMulti;
	}

	public static ItemMultiIconOregen getItem(ISlot slot)
	{
		Object itemMulti = HelpersReflect.getStaticField(ItemsOregen.class, getItemUnlocalizedName(slot));
		if (itemMulti == null || itemMulti instanceof Exception) {
			throw new RuntimeException((Exception) itemMulti);
		}

		return (ItemMultiIconOregen) itemMulti;
	}

	public static int getMetablockId(EnumGlobGroup enumGlobGroup, EnumGlob enumGlob)
	{
		return enumGlobGroup.globGroup.getGlobs().indexOf(enumGlob.glob);
	}

	public static int getMetaitemId(EnumGlobGroup enumGlobGroup, EnumGlob enumGlob)
	{
		return enumGlobGroup.ordinal() * 16 + enumGlobGroup.globGroup.getGlobs().indexOf(enumGlob.glob);
	}

	public static void overrideMetablock(EnumSlot enumSlot, EnumGlobGroup enumGlobGroup, EnumGlob enumGlob,
		MetablockOregen metablock)
	{
		BlockMultiOregen blockMulti = getBlock(enumSlot.slot, enumGlobGroup.globGroup);
		int metaId = getMetablockId(enumGlobGroup, enumGlob);

		blockMulti.multi.clearBindind(metaId);
		blockMulti.multi.bind(enumGlobGroup.globGroup.getGlobs().indexOf(enumGlob), metablock);
	}

	public static void overrideMetaitem(EnumSlot enumSlot, EnumGlobGroup enumGlobGroup, EnumGlob enumGlob,
		MetaitemIconOregen metaitem)
	{
		ItemMultiIconOregen itemMulti = getItem(enumSlot.slot);
		int metaId = getMetaitemId(enumGlobGroup, enumGlob);

		itemMulti.multi.clearBindind(metaId);
		itemMulti.multi.bind(metaId, metaitem);
	}

}
