package mirrg.moddumper1.core;

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
		HelpersDump.eachAllItemStacks(new ICallable1<ItemStack>() {

			@Override
			public void call(ItemStack object)
			{
				AspectList objectTags;
				try {
					objectTags = getObjectTags(object);
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

	public static AspectList getObjectTags(ItemStack arg0) throws Exception
	{
		return (AspectList) Class.forName("thaumcraft.common.lib.crafting.ThaumcraftCraftingManager")
			.getMethod("getObjectTags", ItemStack.class).invoke(null, arg0);
	}

}
