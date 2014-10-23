package mirrg.miragecrops4.oregen;

import static mirrg.miragecrops4.oregen.global.HelpersModuleOregen.*;
import static mirrg.miragecrops4.oregen.global.ItemsOregen.*;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.reflect.HelpersReflect;
import mirrg.mir41.glob.HelpersGlob;
import mirrg.miragecrops4.oregen.global.ItemsOregen;
import mirrg.miragecrops4.oregen.global.MultiIcons;
import mirrg.miragecrops4.oregen.global.ItemsOregen.EnumSlotType;
import mirrg.miragecrops4.oregen.global.ItemsOregen.GlobGroups;
import mirrg.miragecrops4.oregen.global.ItemsOregen.Globs;
import mirrg.miragecrops4.oregen.global.ItemsOregen.Slots;
import mirrg.miragecrops4.oregen.global.RegisterMaterialColor;
import mirrg.miragecrops4.oregen.multi.BlockMultiMirageCrops;
import mirrg.miragecrops4.oregen.multi.ItemBlockMultiMirageCrops;
import mirrg.miragecrops4.oregen.multi.ItemMultiIconMirageCrops;
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
	protected void registerBlocks()
	{

		for (Slots enumSlot : ItemsOregen.Slots.values()) {
			if (enumSlot.type == EnumSlotType.BLOCK) {

				for (GlobGroups enumGlobGroup : ItemsOregen.GlobGroups.values()) {
					if (enumGlobGroup.globGroup.allowsSlot(enumSlot.slot)) {

						String unlocalizedName = getBlockUnlocalizedName(enumSlot.slot, enumGlobGroup.globGroup);

						Exception e = HelpersReflect.setStaticField(
							ItemsOregen.class,
							unlocalizedName,
							registerBlock(new BlockMultiMirageCrops.Raw(Material.rock),
								ItemBlockMultiMirageCrops.class,
								unlocalizedName));
						if (e != null) throw new RuntimeException(e);

					}
				}

			}
		}

	}

	/**
	 * 各種インスタンス生成と登録とAPIへの代入
	 */
	@Override
	protected void registerItems()
	{

		for (Slots enumSlot : ItemsOregen.Slots.values()) {
			if (enumSlot.type == EnumSlotType.ITEM) {

				String unlocalizedName = getItemUnlocalizedName(enumSlot.slot);

				Exception e = HelpersReflect.setStaticField(ItemsOregen.class, unlocalizedName,
					registerItem(new ItemMultiIconMirageCrops.Raw(), unlocalizedName));
				if (e != null) throw new RuntimeException(e);

			}
		}

	}

	@Override
	protected void configureBlocks()
	{

		for (Slots enumSlot : ItemsOregen.Slots.values()) {
			if (enumSlot.type == EnumSlotType.BLOCK) {

				for (GlobGroups enumGlobGroup : ItemsOregen.GlobGroups.values()) {
					if (enumGlobGroup.globGroup.allowsSlot(enumSlot.slot)) {

						String unlocalizedName = getBlockUnlocalizedName(enumSlot.slot, enumGlobGroup.globGroup);

						Object obj = HelpersReflect.getStaticField(ItemsOregen.class, unlocalizedName);

						if (obj == null || obj instanceof Exception) {
							throw new RuntimeException((Exception) obj);
						}

						configureBlock((Block) obj, unlocalizedName);

						createMetaBlock(enumGlobGroup, (BlockMultiMirageCrops.Raw) obj, enumSlot.slot);

					}
				}

			}
		}

	}

	@Override
	protected void configureItems()
	{

		for (Slots enumSlot : ItemsOregen.Slots.values()) {
			if (enumSlot.type == EnumSlotType.ITEM) {

				String unlocalizedName = getItemUnlocalizedName(enumSlot.slot);

				Object obj = HelpersReflect.getStaticField(ItemsOregen.class, unlocalizedName);

				if (obj == null || obj instanceof Exception) {
					throw new RuntimeException((Exception) obj);
				}

				configureItem((Item) obj, unlocalizedName);

				createMetaItem(ItemsOregen.GlobGroups.values(), (ItemMultiIconMirageCrops.Raw) obj, enumSlot.slot,
					enumSlot.icon());

			}
		}

	}

	/**
	 * registerBlocksの後
	 */
	protected void registerWorldgen()
	{

		//           mi ma   CPC  nu  bio   ore
		rwfcpc(this, 0, 128, 0.4, 32, null, cpo(Globs.calcite));
		rwfcpc(this, 0, 96, 1.8, 8, null, cpo(Globs.magnesite));
		rwfcpc(this, 0, 80, 1.6, 7, null, cpo(Globs.siderite));
		rwfcpc(this, 0, 64, 1.4, 6, null, cpo(Globs.smithsonite));
		rwfcpc(this, 0, 48, 0.9, 6, "ocean", cpo(Globs.rhodochrosite));
		rwfcpc(this, 0, 32, 0.7, 5, "forest", cpo(Globs.sphaerocobaltite));
		rwfcpc(this, 0, 24, 0.5, 4, "desert", cpo(Globs.gaspeite));
		rwfcpc(this, 0, 16, 0.5, 1, "extreme", cpo(Globs.otavite));
		rwfcpc(this, 0, 60, 0.1, 4, null, cpo(Globs.bismuth));
		rwfcpc(this, 0, 60, 0.3, 8, null, cpo(Globs.spinatite));
		rwfcpc(this, 64, 128, 0.8, 18, "extreme", cpo(Globs.apatite));
		rwfcpc(this, 64, 128, 1.5, 8, "extreme", cpo(Globs.fluorite));

	}

	private void rwfcpc(ModuleOregenBase moduleOregenBase,
		int minHeight, int maxHeight, double countPerCube, double numberOfBlocks, String biome,
		ItemStack ore)
	{
		moduleOregenBase.registerWorldgenFromCountPerCube(
			minHeight, maxHeight, countPerCube, numberOfBlocks, biome, ore);
	}

	private ItemStack cp(Slots enumSlot, Globs enumGlob)
	{
		return HelpersGlob.copy(globManager, enumSlot.slot, enumGlob.glob);
	}

	private ItemStack cpo(Globs enumGlob)
	{
		return cp(Slots.ore, enumGlob);
	}

}
