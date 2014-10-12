package mirrg.moddumper1.core.dumper;

import java.lang.reflect.Method;

import mirrg.moddumper1.HelpersDump;
import mirrg.moddumper1.ICallable1;
import mirrg.moddumper1.IDumper;
import mirrg.moddumper1.ILogger;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class DumperThaumCraft implements IDumper
{

	@Override
	public String getName()
	{
		return "dumperThaumCraft";
	}

	@Override
	public void onDump(final ILogger logger)
	{
		if (gethod_getObjectTags == null) {
			logger.log("Thaumcraft is not available");
			return;
		}

		HelpersDump.eachAllItemStacks(new ICallable1<ItemStack>() {

			@Override
			public void call(ItemStack object)
			{
				AspectList objectTags;
				try {
					objectTags = (AspectList) gethod_getObjectTags.invoke(null, object);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				if (objectTags == null) return;

				try {
					logger.log("ITEM,%s,V,'%s'",
						HelpersDump.getItemStackString(object),
						getAspectListString(objectTags));
				} catch (Exception e) {
					logger.log(e.toString());
				}
			}

		});
	}

	public static String getAspectListString(AspectList tags)
	{
		Aspect[] aspectsSortedAmount = tags.getAspectsSortedAmount();
		StringBuffer sb = new StringBuffer();
		sb.append("[[");
		for (Aspect as : aspectsSortedAmount) {
			try {
				sb.append(" " + as.getName() + "(" + tags.getAmount(as) + ")");
			} catch (Exception e) {
				sb.append(e.getClass().getName());
			}
		}
		sb.append(" ]]");
		return sb.toString();
	}

	public static Method gethod_getObjectTags = null;

	static
	{
		try {
			gethod_getObjectTags =
				(Method) Class.forName(
					"thaumcraft.common.lib.crafting.ThaumcraftCraftingManager")
					.getMethod("getObjectTags", ItemStack.class);
		} catch (Exception e) {
		}
	}

}
