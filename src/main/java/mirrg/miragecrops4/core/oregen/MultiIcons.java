package mirrg.miragecrops4.core.oregen;

import static mirrg.mir40.icon.MultiIconShape.*;
import mirrg.mir40.icon.MultiIconShape;
import mirrg.mir40.icon.api.IMultiIconShape;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MultiIcons
{

	public static IMultiIconShape DUST;
	public static IMultiIconShape DUST_SMALL;
	public static IMultiIconShape DUST_TINY;
	public static IMultiIconShape INGOT;
	public static IMultiIconShape NUGGET;
	public static IMultiIconShape MACHINE_HULL;
	public static IMultiIconShape PLATE;
	public static IMultiIconShape ROD;
	public static IMultiIconShape WIRE;
	public static IMultiIconShape GEM;

	public static IMultiIconShape SCYTHE;
	public static IMultiIconShape WRENCH;
	public static IMultiIconShape HAMMER;
	public static IMultiIconShape FILE;
	public static IMultiIconShape MORTAR;
	public static IMultiIconShape CHISEL;
	public static IMultiIconShape SAW;
	public static IMultiIconShape CUTTER;

	public static IMultiIconShape BUCKET;

	@SideOnly(Side.CLIENT)
	public static void registerMultiIconShapes(String texturePrefix)
	{

		DUST = new MultiIconShape(texturePrefix + "dust",
			entry("line", 0.5),
			entry("background", 1.0),
			entry("dark", 0.75),
			entry("light", 1.25));

		DUST_SMALL = new MultiIconShape(texturePrefix + "dustSmall",
			entry("line", 0.5),
			entry("background", 1.0),
			entry("dark", 0.75),
			entry("light", 1.25));

		DUST_TINY = new MultiIconShape(texturePrefix + "dustTiny",
			entry("line", 0.5),
			entry("background", 1.0),
			entry("dark", 0.75),
			entry("light", 1.25));

		INGOT = new MultiIconShape(texturePrefix + "ingot",
			entry("line", 0.69),
			entry("background", 1.0),
			entry("dark", 0.53),
			entry("light", 1.5));

		NUGGET = new MultiIconShape(texturePrefix + "nugget",
			entry("line", 0.69),
			entry("background", 1.0),
			entry("dark", 0.53),
			entry("light", 1.5));

		MACHINE_HULL = new MultiIconShape(texturePrefix + "machineHull",
			entry("line", 0.25),
			entry("background", 1.0),
			entry("light", 2.0),
			entry("logo_mirrg", 0.75));

		PLATE = new MultiIconShape(texturePrefix + "plate",
			entry("line", 0.69),
			entry("background", 1.0),
			entry("light", 1.5));

		ROD = new MultiIconShape(texturePrefix + "rod",
			entry("line", 0.69),
			entry("background", 1.0));

		WIRE = new MultiIconShape(texturePrefix + "wire",
			entry("", 1.0));

		GEM = new MultiIconShape(texturePrefix + "gem",
			entry("line", 0.4),
			entry("background", 1.0),
			entry("light", 1.7));

		//

		SCYTHE = new MultiIconShape(texturePrefix + "scythe",
			entry("blade", 1.0),
			entry("stick", 1.0, 1));

		WRENCH = new MultiIconShape(texturePrefix + "wrench",
			entry("", 1.0));

		HAMMER = new MultiIconShape(texturePrefix + "hammer",
			entry("stick", 1.0, 1),
			entry("head", 1.0),
			entry("light", 1.5));

		FILE = new MultiIconShape(texturePrefix + "file",
			entry("stick", 1.0, 1),
			entry("head", 1.0));

		MORTAR = new MultiIconShape(texturePrefix + "mortar",
			entry("bowl_background", 1.0, 1),
			entry("iron_background", 1.0),
			entry("bowl_light", 1.4, 1),
			entry("iron_light", 1.6));

		CHISEL = new MultiIconShape(texturePrefix + "chisel",
			entry("stick", 1.0, 1),
			entry("head", 1.0));

		SAW = new MultiIconShape(texturePrefix + "saw",
			entry("holding", 1.0, 1),
			entry("blade", 1.0));

		CUTTER = new MultiIconShape(texturePrefix + "cutter",
			entry("stick_background", 1.0, 1),
			entry("head_background", 1.0),
			entry("stick_line", 0.65, 1),
			entry("head_line", 0.65));

		//

		BUCKET = new MultiIconShape(texturePrefix + "bucket",
			entry("body", 1.0, 1),
			entry("fluid", 1.0));

	}

}
