package mirrg.miragecrops4.core.fairy.crop;

import ic2.api.crops.ICropTile;
import mirrg.mir40.data.EnumNBTTypes;
import mirrg.miragecrops4.core.crop.CropMirage;
import net.minecraft.nbt.NBTTagCompound;

/**
 * <pre>
 * crop.getCustomData()
 * └ "fairy" : COMPOUND
 * 　├ "version" : INT
 * 　├ other properties
 * 　└ other properties
 * </pre>
 */
public abstract class CropMirageFairy<T extends IFairyTag> extends CropMirage
{

	protected T loadFairyTag(ICropTile crop)
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

		T fairyTag = createFairyTag();
		fairyTag.readFromNBT(fairy);

		return fairyTag;
	}

	protected void deleteFairyTag(ICropTile crop)
	{
		if (crop.getCustomData().hasKey("fairy")) {
			crop.getCustomData().removeTag("fairy");
		}
	}

	protected void saveFairyTag(ICropTile crop, T fairyHousing)
	{
		NBTTagCompound fairy = new NBTTagCompound();
		fairy.setInteger("version", 1);
		fairyHousing.writeToNBT(fairy);
		crop.getCustomData().setTag("fairy", fairy);
	}

	protected abstract T createFairyTag();

	protected abstract int getCurrentVersion();

}
