package mirrg.moddumper1.core;

import java.lang.reflect.Method;

import mirrg.mir34.modding.IMod;
import mirrg.moddumper1.HelpersDump;
import mirrg.moddumper1.ICallable1;
import mirrg.moddumper1.ModuleDumperAbstract;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ModuleDumperThaumCraft extends ModuleDumperAbstract
{

	public ModuleDumperThaumCraft(IMod mod)
	{
		super(mod);
	}

	@Override
	public String getModuleName()
	{
		return "dumperThaumCraft";
	}

	@Override
	protected void onDump()
	{
		if (gethod_getObjectTags == null) {
			log("Thaumcraft is not available");
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
					log("ITEM,%s,V,'%s'",
						HelpersDump.getItemStackString(object),
						getAspectListString(objectTags));
				} catch (Exception e) {
					log(e.toString());
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
