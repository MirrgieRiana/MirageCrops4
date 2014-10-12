package mirrg.moddumper1;

import java.util.LinkedList;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;

public abstract class ModuleDumperAbstract extends ModuleAbstract implements ILoadCompleteHandler
{

	public ModuleDumperAbstract(IMod mod)
	{
		super(mod);
	}

	@Override
	public void handle(FMLLoadCompleteEvent event)
	{
		processDraw();
	}

	protected void processDraw()
	{
		FMLLog.info("[%s] Draw Start", getModuleName());

		onLoadComplete();

		FMLLog.info("[%s] Draw Start", getModuleName());
	}

	protected abstract void onLoadComplete();

	public static String getItemStackString(ItemStack itemStack)
	{
		return String.format(
			"LN-UL-ID-DA,'%s','%s','%s','%s'",
			itemStack.getDisplayName(),
			itemStack.getItem().getUnlocalizedName(),
			Item.getIdFromItem(itemStack.getItem()),
			itemStack.getItemDamage());
	}

	public static void eachAllItemStacks(ICallable1<ItemStack> handler)
	{

		for (int i = 0; i < 65536; i++) {

			Item item;
			try {
				item = Item.getItemById(i);
			} catch (Exception e) {
				FMLLog.info(e.toString());
				continue;
			}

			if (item == null) continue;

			LinkedList<ItemStack> list = new LinkedList<ItemStack>();
			try {
				item.getSubItems(item, null, list);
			} catch (Exception e) {
				FMLLog.info(e.toString());
				continue;
			}

			for (ItemStack itemStack : list) {

				if (itemStack == null) continue;

				try {
					handler.call(itemStack);
				} catch (RuntimeException e) {
					FMLLog.info(e.toString());
					continue;
				}

			}

			try {
			} catch (Exception e) {
				FMLLog.info(e.toString());
			}
		}

	}

}
