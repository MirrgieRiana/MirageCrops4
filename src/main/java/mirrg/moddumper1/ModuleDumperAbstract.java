package mirrg.moddumper1;

import java.util.LinkedList;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class ModuleDumperAbstract extends ModuleAbstract implements ILoadCompleteHandler
{

	public ModuleDumperAbstract(IMod mod)
	{
		super(mod);
	}

	protected boolean enabled = false;

	@Override
	public void handle(FMLPreInitializationEvent event)
	{
		enabled = ((ModModDumper) getMod()).configuration.getBoolean(getModuleName(), "module", false,
			"if true, output dump data when LoadComplete");
	}

	@Override
	public void handle(FMLLoadCompleteEvent event)
	{
		processDump();
	}

	/**
	 * ダンプ命令。コンフィグで処理内容が変わる
	 */
	protected void processDump()
	{
		if (enabled) {
			FMLLog.info("[%s] Dump Start", getModuleName());
			onDump();
			FMLLog.info("[%s] Dump Start", getModuleName());
		} else {
			FMLLog.info("[%s] Dump Canceled", getModuleName());
		}
	}

	/**
	 * ダンプ処理。{@link ModuleDumperAbstract#processDump}から呼び出される
	 */
	protected abstract void onDump();

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
