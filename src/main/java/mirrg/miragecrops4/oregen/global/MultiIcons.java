package mirrg.miragecrops4.oregen.global;

import static mirrg.mir40.icon.MultiIconShape.*;
import static mirrg.miragecrops4.oregen.global.MultiIcons.EnumMultiIconShape.*;
import mirrg.mir40.icon.IMultiIconShape;
import mirrg.mir40.icon.MultiIconShape;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MultiIcons
{

	public static enum EnumMultiIconShape
	{
		DUST,
		DUST_SMALL,
		DUST_TINY,
		INGOT,
		NUGGET,
		MACHINE_HULL,
		PLATE,
		ROD,
		WIRE,
		GEM,

		SCYTHE,
		WRENCH,
		HAMMER,
		FILE,
		MORTAR,
		CHISEL,
		SAW,
		CUTTER,

		BUCKET, ;

		public IMultiIconShape shape;

	}

	@SideOnly(Side.CLIENT)
	public static void registerMultiIconShapes(String texturePrefix)
	{

		DUST.shape = new MultiIconShape(texturePrefix + "dust",
			entry("line", 0.5),
			entry("background", 1.0),
			entry("dark", 0.75),
			entry("light", 1.25));

		DUST_SMALL.shape = new MultiIconShape(texturePrefix + "dustSmall",
			entry("line", 0.5),
			entry("background", 1.0),
			entry("dark", 0.75),
			entry("light", 1.25));

		DUST_TINY.shape = new MultiIconShape(texturePrefix + "dustTiny",
			entry("line", 0.5),
			entry("background", 1.0),
			entry("dark", 0.75),
			entry("light", 1.25));

		INGOT.shape = new MultiIconShape(texturePrefix + "ingot",
			entry("line", 0.69),
			entry("background", 1.0),
			entry("dark", 0.53),
			entry("light", 1.5));

		NUGGET.shape = new MultiIconShape(texturePrefix + "nugget",
			entry("line", 0.69),
			entry("background", 1.0),
			entry("dark", 0.53),
			entry("light", 1.5));

		MACHINE_HULL.shape = new MultiIconShape(texturePrefix + "machineHull",
			entry("line", 0.25),
			entry("background", 1.0),
			entry("light", 2.0),
			entry("logo_mirrg", 0.75));

		PLATE.shape = new MultiIconShape(texturePrefix + "plate",
			entry("line", 0.69),
			entry("background", 1.0),
			entry("light", 1.5));

		ROD.shape = new MultiIconShape(texturePrefix + "rod",
			entry("line", 0.69),
			entry("background", 1.0));

		WIRE.shape = new MultiIconShape(texturePrefix + "wire",
			entry("", 1.0));

		GEM.shape = new MultiIconShape(texturePrefix + "gem",
			entry("line", 0.4),
			entry("background", 1.0),
			entry("light", 1.7));

		//

		SCYTHE.shape = new MultiIconShape(texturePrefix + "scythe",
			entry("blade", 1.0),
			entry("stick", 1.0, 1));

		WRENCH.shape = new MultiIconShape(texturePrefix + "wrench",
			entry("", 1.0));

		HAMMER.shape = new MultiIconShape(texturePrefix + "hammer",
			entry("stick", 1.0, 1),
			entry("head", 1.0),
			entry("light", 1.5));

		FILE.shape = new MultiIconShape(texturePrefix + "file",
			entry("stick", 1.0, 1),
			entry("head", 1.0));

		MORTAR.shape = new MultiIconShape(texturePrefix + "mortar",
			entry("bowl_background", 1.0, 1),
			entry("iron_background", 1.0),
			entry("bowl_light", 1.4, 1),
			entry("iron_light", 1.6));

		CHISEL.shape = new MultiIconShape(texturePrefix + "chisel",
			entry("stick", 1.0, 1),
			entry("head", 1.0));

		SAW.shape = new MultiIconShape(texturePrefix + "saw",
			entry("holding", 1.0, 1),
			entry("blade", 1.0));

		CUTTER.shape = new MultiIconShape(texturePrefix + "cutter",
			entry("stick_background", 1.0, 1),
			entry("head_background", 1.0),
			entry("stick_line", 0.65, 1),
			entry("head_line", 0.65));

		//

		BUCKET.shape = new MultiIconShape(texturePrefix + "bucket",
			entry("body", 1.0, 1),
			entry("fluid", 1.0));

	}

}
