package mirrg.mir40.itemstack;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class HelpersItemStack
{

	public static boolean isItemStackEqual_ItemDamageNBTSize(ItemStack a, ItemStack b)
	{
		if (!isItemStackEqual_ItemDamageNBT(a, b)) return false;

		if (a == null) return b == null;
		if (b == null) return false;

		return a.stackSize == b.stackSize;
	}

	public static boolean isItemStackEqual_ItemDamageNBT(ItemStack a, ItemStack b)
	{
		if (!isItemStackEqual_ItemDamage(a, b)) return false;

		if (a == null) return b == null;
		if (b == null) return false;

		if (a.stackTagCompound == null) return b.stackTagCompound == null;
		if (b.stackTagCompound == null) return false;

		return a.stackTagCompound.equals(b.stackTagCompound);
	}

	public static boolean isItemStackEqual_ItemDamage(ItemStack a, ItemStack b)
	{
		if (!isItemStackEqual_Item(a, b)) return false;

		if (a == null) return b == null;
		if (b == null) return false;

		return a.getItemDamage() == b.getItemDamage();
	}

	public static boolean isItemStackEqual_Item(ItemStack a, ItemStack b)
	{
		if (a == null) return b == null;
		if (b == null) return false;

		return a.getItem() == b.getItem();
	}

	/**
	 * dictionaryOreのダメージ値が32767の場合、
	 * ダメージ値無視でitemStackとdictionaryOreが等しい場合にtrueを返す。
	 * dictionaryOreのダメージ値が32767でない場合、
	 * ダメージ値込みでitemStackとdictionaryOreが等しい場合にtrueを返す。
	 */
	public static boolean isOre(ItemStack itemStack, ItemStack dictionaryOre)
	{
		if (itemStack == null) return false;

		if (dictionaryOre.getItemDamage() == 32767) {
			if (dictionaryOre.getItem() == itemStack.getItem()) {
				return true;
			}
		} else {
			if (dictionaryOre.getItem() == itemStack.getItem()) {
				if (dictionaryOre.getItemDamage() == itemStack.getItemDamage()) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * dictionaryNameで与えられるItemStackのリストのうち、どれか一つがマッチしていた場合trueを返す（
	 * {@link SItemStack#isOre(ItemStack, ItemStack)}）。
	 */
	public static boolean isOre(ItemStack itemStack, String dictionaryName)
	{
		if (itemStack == null) return false;

		ArrayList<ItemStack> ores = OreDictionary.getOres(dictionaryName);
		for (ItemStack ore : ores) {
			return isOre(itemStack, ore);
		}

		return false;
	}

	/**
	 * targetがnullの場合、falseを返す。 targetが{@link ItemStack}
	 * の場合、それがdictionaryNameにマッチする場合trueを返す（
	 * {@link SItemStack#isOre(ItemStack, String)}）。
	 * targetがStringの場合、それがdictionaryNameと等しい場合trueを返す。
	 * targetがArrayListの場合、各要素がisOreである場合trueを返す。 それ以外の場合、falseを返す。
	 */
	public static boolean isOre(Object target, String dictionaryName)
	{
		if (target == null) {
			return false;
		} else if (target instanceof ItemStack) {
			return isOre((ItemStack) target, dictionaryName);
		} else if (target instanceof String) {
			return target.equals(dictionaryName);
		} else if (target instanceof ArrayList) {

			for (Object item : (ArrayList<?>) target) {
				if (!isOre(item, dictionaryName)) return false;
			}

			return true;
		} else {
			return false;
		}
	}

}
