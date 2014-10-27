package mirrg.miragecrops4.lib.oregen;

import mirrg.mir40.math.HelpersString;
import mirrg.mir40.reflect.HelpersReflect;
import mirrg.mir41.glob.IGlob;
import mirrg.mir41.glob.IGlobGroup;
import mirrg.mir41.glob.ISlot;
import mirrg.miragecrops4.lib.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.multi.MetablockOregen;
import mirrg.miragecrops4.lib.oregen.GlobsOregen.EnumSlot;

public class HelpersOregen
{

	public static void overrideMetablock(ISlot slot, IGlobGroup<?> globGroup, IGlob glob, MetablockOregen metablock)
	{
		Object blockMulti = HelpersReflect.getStaticField(
			ItemsOregen.class, HelpersOregen.getBlockUnlocalizedName(slot, globGroup));
		if (blockMulti == null || blockMulti instanceof Exception) {
			throw new RuntimeException((Exception) blockMulti);
		}

		((BlockMultiOregen) blockMulti).multi.clearBindind(globGroup.getGlobs().indexOf(glob));
		((BlockMultiOregen) blockMulti).multi.bind(globGroup.getGlobs().indexOf(glob), metablock);
	}

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

}
