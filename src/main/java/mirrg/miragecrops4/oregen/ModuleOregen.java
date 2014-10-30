package mirrg.miragecrops4.oregen;

import mirrg.h.multi.Multi;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.reflect.HelpersReflect;
import mirrg.miragecrops4.lib.RegisterMaterialColor;
import mirrg.miragecrops4.lib.oregen.GlobsOregen;
import mirrg.miragecrops4.lib.oregen.GlobsOregen.EnumGlobGroup;
import mirrg.miragecrops4.lib.oregen.GlobsOregen.EnumSlot;
import mirrg.miragecrops4.lib.oregen.ItemsOregen;
import mirrg.miragecrops4.lib.oregen.MultiIcons;
import mirrg.miragecrops4.lib.oregen.multi.BlockMultiOregen;
import mirrg.miragecrops4.lib.oregen.multi.IMetablockOregen;
import mirrg.miragecrops4.lib.oregen.multi.IMetaitemIconOregen;
import mirrg.miragecrops4.lib.oregen.multi.ItemBlockMultiOregen;
import mirrg.miragecrops4.lib.oregen.multi.ItemMultiIconOregen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleOregen extends ModuleOregenBase
{

	public ModuleOregen(IMod mod)
	{
		super(mod);

		ItemsOregen.moduleOregen = this;
	}

	@Override
	public String getModuleName()
	{
		return "oregen";
	}

	//

	@Override
	@SideOnly(Side.CLIENT)
	public void handlePreClient(FMLPreInitializationEvent event)
	{

		super.handlePreClient(event);

		MultiIcons.registerMultiIconShapes(getMod().getModId() + ":" + getModuleName() + "/multi/");

	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		RegisterMaterialColor.registerMaterialColors();

		super.handle(event);

		registerWorldgen();

	}

	//

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerBlocks(EnumSlot enumSlot, EnumGlobGroup enumGlobGroup, String unlocalizedName)
	{
		Exception e = HelpersReflect.setStaticField(
			ItemsOregen.class,
			unlocalizedName,
			registerBlock(new BlockMultiOregen(Material.rock, new Multi(new IMetablockOregen[16])),
				ItemBlockMultiOregen.class,
				unlocalizedName));
		if (e != null) throw new RuntimeException(e);
	}

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerItems(EnumSlot enumSlot, String unlocalizedName)
	{
		Exception e = HelpersReflect.setStaticField(
			ItemsOregen.class,
			unlocalizedName,
			registerItem(new ItemMultiIconOregen(new Multi(new IMetaitemIconOregen[256])),
				unlocalizedName));
		if (e != null) throw new RuntimeException(e);
	}

	@Override
	protected void configureBlocks(EnumSlot enumSlot, EnumGlobGroup enumGlobGroup, String unlocalizedName)
	{
		Object obj = HelpersReflect.getStaticField(ItemsOregen.class, unlocalizedName);
		if (obj == null || obj instanceof Exception) throw new RuntimeException((Exception) obj);

		configureBlock((Block) obj, unlocalizedName);

		createMetaBlock(enumGlobGroup, (BlockMultiOregen) obj, enumSlot.slot);

	}

	@Override
	protected void configureItems(EnumSlot enumSlot, String unlocalizedName)
	{
		Object obj = HelpersReflect.getStaticField(ItemsOregen.class, unlocalizedName);
		if (obj == null || obj instanceof Exception) throw new RuntimeException((Exception) obj);

		configureItem((Item) obj, unlocalizedName);

		createMetaItem(GlobsOregen.EnumGlobGroup.values(), (ItemMultiIconOregen) obj, enumSlot.slot,
			enumSlot.icon());

	}

	/**
	 * registerBlocksの後
	 */
	protected void registerWorldgen()
	{

		//     mi ma   CPC  nu  bio   ore
		rwfcpc(0, 128, 0.4, 32, null, cpo(GlobsOregen.EnumGlob.calcite));
		rwfcpc(0, 96, 1.8, 8, null, cpo(GlobsOregen.EnumGlob.magnesite));
		rwfcpc(0, 80, 1.6, 7, null, cpo(GlobsOregen.EnumGlob.siderite));
		rwfcpc(0, 64, 1.4, 6, null, cpo(GlobsOregen.EnumGlob.smithsonite));
		rwfcpc(0, 48, 0.9, 6, "ocean", cpo(GlobsOregen.EnumGlob.rhodochrosite));
		rwfcpc(0, 32, 0.7, 5, "forest", cpo(GlobsOregen.EnumGlob.sphaerocobaltite));
		rwfcpc(0, 24, 0.5, 4, "desert", cpo(GlobsOregen.EnumGlob.gaspeite));
		rwfcpc(0, 16, 0.5, 1, "extreme", cpo(GlobsOregen.EnumGlob.otavite));
		rwfcpc(0, 60, 0.1, 4, null, cpo(GlobsOregen.EnumGlob.bismuth));
		rwfcpc(0, 60, 0.3, 8, null, cpo(GlobsOregen.EnumGlob.spinatite));
		rwfcpc(64, 128, 0.8, 18, "extreme", cpo(GlobsOregen.EnumGlob.apatite));
		rwfcpc(64, 128, 1.5, 8, "extreme", cpo(GlobsOregen.EnumGlob.fluorite));

	}

	private void rwfcpc(
		int minHeight, int maxHeight, double countPerCube, double numberOfBlocks, String biome,
		ItemStack ore)
	{
		registerWorldgenFromCountPerCube(
			minHeight, maxHeight, countPerCube, numberOfBlocks, biome, ore);
	}

	private ItemStack cpo(GlobsOregen.EnumGlob enumGlob)
	{
		return cpy(GlobsOregen.EnumSlot.ore, enumGlob);
	}

}
