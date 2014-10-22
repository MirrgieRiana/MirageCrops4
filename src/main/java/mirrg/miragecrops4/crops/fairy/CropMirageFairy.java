package mirrg.miragecrops4.crops.fairy;

import ic2.api.crops.ICropTile;
import mirrg.mir40.data.EnumNBTTypes;
import mirrg.miragecrops4.crops.CropMirageCropsBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * オーバーライドはしない。カスタムデータの構築に関するヘルパーのみ提供する。有効・不正・削除状態がある。
 * 
 * <pre>
 * crop.getCustomData()
 * └ "fairy" : COMPOUND
 * 　├ "version" : INT
 * 　├ other properties
 * 　└ other properties
 * </pre>
 */
public abstract class CropMirageFairy<T extends IFairyTag> extends CropMirageCropsBase
{

	/**
	 * フェアリータグのNBTが有効な場合、これを返す。削除、不正状態の場合はnullを返す。
	 */
	protected NBTTagCompound validateFairyTag(ICropTile crop)
	{
		if (!crop.getCustomData().hasKey("fairy", EnumNBTTypes.COMPOUND.ordinal())) {
			return null;
		}
		NBTTagCompound fairy = crop.getCustomData().getCompoundTag("fairy");

		if (!fairy.hasKey("version", EnumNBTTypes.INT.ordinal())) {
			return null;
		}
		int version = fairy.getInteger("version");

		if (version != getCurrentVersion()) {
			return null;
		}

		return fairy;
	}

	/**
	 * フェアリータグが有効であれば読み取る。有効でない場合、nullを返す。
	 */
	protected T loadFairyTag(ICropTile crop)
	{
		NBTTagCompound validateFairyTag = validateFairyTag(crop);
		if (validateFairyTag == null) return null;

		T fairyTag = createFairyTag();
		fairyTag.readFromNBT(validateFairyTag);

		return fairyTag;
	}

	/**
	 * フェアリータグを削除して初期状態に戻す。削除済み、不正なデータ時に行うと初期状態に戻る。
	 */
	protected void deleteFairyTag(ICropTile crop)
	{
		if (crop.getCustomData().hasKey("fairy")) {
			crop.getCustomData().removeTag("fairy");
		}
	}

	/**
	 * フェアリータグを上書きする。削除、不正、有効時に行うと有効状態になる。
	 */
	protected void saveFairyTag(ICropTile crop, T fairyTag)
	{
		NBTTagCompound fairy = new NBTTagCompound();
		fairy.setInteger("version", getCurrentVersion());
		fairyTag.writeToNBT(fairy);
		crop.getCustomData().setTag("fairy", fairy);
	}

	protected abstract T createFairyTag();

	protected abstract int getCurrentVersion();

}
