package mirrg.miragecrops4.oregen.global;

import java.util.Hashtable;

public class RegisterMaterialColor
{

	public static Hashtable<String, Integer> instance = new Hashtable<String, Integer>();

	public static void registerMaterialColor(String name, int color)
	{
		instance.put(name, color);
	}

	public static void registerMaterialColors()
	{

		registerMaterialColor("calcite", 0xDBE0BE);
		registerMaterialColor("magnesite", 0xDAE1F0);
		registerMaterialColor("siderite", 0x91A42C);
		registerMaterialColor("rhodochrosite", 0xE594BB);
		registerMaterialColor("smithsonite", 0x97DDBF);
		registerMaterialColor("sphaerocobaltite", 0xC0278B);
		registerMaterialColor("gaspeite", 0x406F2F);
		registerMaterialColor("otavite", 0x95ABE8);

		registerMaterialColor("calcium", 0xE0E0D5);
		registerMaterialColor("magnesium", 0xD8A9A9);
		registerMaterialColor("iron", 0xA5A5A5);
		registerMaterialColor("manganese", 0xC8C0C0);
		registerMaterialColor("zinc", 0xE5DBF2);
		registerMaterialColor("cobalt", 0x4242CF);
		registerMaterialColor("nickel", 0x96A4FF);
		registerMaterialColor("cadmium", 0x2F2F38);

		registerMaterialColor("glass", 0xEEEEEE);

		registerMaterialColor("bismuth", 0x597ABA);
		registerMaterialColor("spinatite", 0x137F18);
		registerMaterialColor("spinachium", 0x00C610);
		registerMaterialColor("mirageAlloy", 0x548327);

		registerMaterialColor("talc", 0xD3E2D7);
		registerMaterialColor("gypsum", 0xEAEAEA);
		registerMaterialColor("fluorite", 0x1BE86A);
		registerMaterialColor("apatite", 0x5BB5FF);
		registerMaterialColor("orthoclase", 0xFFE2B7);
		registerMaterialColor("certusQuartz", 0xC6D0FF);
		registerMaterialColor("topaz", 0xFFC46D);
		registerMaterialColor("ruby", 0xE40000);
		registerMaterialColor("diamond", 0x33EBCB);

		registerMaterialColor("sulfur", 0xFFE727);

		registerMaterialColor("copper", 0xEF7351);
		registerMaterialColor("tin", 0xCEDBEA);
		registerMaterialColor("gold", 0xFFFF0B);
		registerMaterialColor("silver", 0xD8F4FF);
		registerMaterialColor("lead", 0x5929AD);
		registerMaterialColor("bronze", 0xFF6A00);
		registerMaterialColor("brass", 0xEABE2E);
		registerMaterialColor("electrum", 0xFFFF8C);
		registerMaterialColor("mercury", 0xD5B7B7);
		registerMaterialColor("titanium", 0xD197FF);
		registerMaterialColor("chrome", 0xFFAAD5);
		registerMaterialColor("iridium", 0xE0FFE0);
		registerMaterialColor("osmium", 0x4B00CE);
		registerMaterialColor("steel", 0x74749E);
		registerMaterialColor("stainlessSteel", 0x9EEDBB);
		registerMaterialColor("tungsten", 0x333346);
		registerMaterialColor("tungstenSteel", 0x3636A0);
		registerMaterialColor("platinum", 0xFFF6DD);
		registerMaterialColor("aluminium", 0xC0D3F7);

	}

}
