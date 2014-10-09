package mirrg.mir40.crop.reflect;

import ic2.api.crops.ICropTile;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HelpersReflectCrop
{

	static final Class class_TileEntityCrop;
	static final Field field_upgraded;
	static final Field field_ticker;
	static final Field field_dirty;
	static final Field field_growthPoints;
	static final Method method_calcGrowthRate;

	static {

		try {

			// class initialization
			class_TileEntityCrop = Class.forName("ic2.core.block.TileEntityCrop");

			// class member initialization
			field_upgraded = class_TileEntityCrop.getField("upgraded");
			field_ticker = class_TileEntityCrop.getField("ticker");
			field_dirty = class_TileEntityCrop.getField("dirty");
			field_growthPoints = class_TileEntityCrop.getField("growthPoints");
			method_calcGrowthRate = class_TileEntityCrop.getMethod("calcGrowthRate");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static boolean isUpgraded(ICropTile iCropTile)
	{
		try {
			return field_upgraded.getBoolean(iCropTile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setUpgraded(ICropTile iCropTile, boolean value)
	{
		try {
			field_upgraded.setBoolean(iCropTile, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static char getTicker(ICropTile iCropTile)
	{
		try {
			return field_ticker.getChar(iCropTile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setTicker(ICropTile iCropTile, char value)
	{
		try {
			field_ticker.setChar(iCropTile, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isDirty(ICropTile iCropTile)
	{
		try {
			return field_dirty.getBoolean(iCropTile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setDirty(ICropTile iCropTile, boolean value)
	{
		try {
			field_dirty.setBoolean(iCropTile, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int getGrowthPoints(ICropTile iCropTile)
	{
		try {
			return field_growthPoints.getInt(iCropTile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setGrowthPoints(ICropTile iCropTile, int value)
	{
		try {
			field_growthPoints.setInt(iCropTile, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int calcGrowthRate(ICropTile iCropTile)
	{
		try {
			return Integer.parseInt(method_calcGrowthRate.invoke(iCropTile).toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
