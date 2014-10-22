package mirrg.miragecrops4.oregen;

import static mirrg.miragecrops4.oregen.global.HelpersModuleOregen.*;
import static mirrg.miragecrops4.oregen.global.ItemsOregen.*;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.reflect.HelpersReflect;
import mirrg.mir41.glob.HelpersGlob;
import mirrg.miragecrops4.oregen.global.ItemsOregen;
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
					enumSlot.icon);

			}
		}

	}

	/**
	 * registerBlocksの後
	 */
	protected void registerWorldgen()
	{
		int minHeight;
		int maxHeight;
		double countPerCube;
		double numberOfBlocks;
		ItemStack ore;
		String biome;

		minHeight = 0;
		maxHeight = 128;
		countPerCube = 0.4;
		numberOfBlocks = 32;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.calcite.glob);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//

		minHeight = 0;
		maxHeight = 96;
		countPerCube = 1.8;
		numberOfBlocks = 8;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.magnesite.glob);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 80;
		countPerCube = 1.6;
		numberOfBlocks = 7;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.siderite.glob);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 64;
		countPerCube = 1.4;
		numberOfBlocks = 6;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.smithsonite.glob);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//

		minHeight = 0;
		maxHeight = 48;
		countPerCube = 0.9;
		numberOfBlocks = 6;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.rhodochrosite.glob);
		biome = "ocean";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 32;
		countPerCube = 0.7;
		numberOfBlocks = 5;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.sphaerocobaltite.glob);
		biome = "forest";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 24;
		countPerCube = 0.5;
		numberOfBlocks = 4;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.gaspeite.glob);
		biome = "desert";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//

		minHeight = 0;
		maxHeight = 16;
		countPerCube = 0.5;
		numberOfBlocks = 1;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.otavite.glob);
		biome = "extreme";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//
		//

		minHeight = 0;
		maxHeight = 60;
		countPerCube = 0.1;
		numberOfBlocks = 4;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.bismuth.glob);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 0;
		maxHeight = 60;
		countPerCube = 0.3;
		numberOfBlocks = 8;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.spinatite.glob);
		biome = null;
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		//
		//

		minHeight = 64;
		maxHeight = 128;
		countPerCube = 0.8;
		numberOfBlocks = 18;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.apatite.glob);
		biome = "extreme";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

		minHeight = 64;
		maxHeight = 128;
		countPerCube = 1.5;
		numberOfBlocks = 8;
		ore = HelpersGlob.copy(globManager, Slots.ore.slot, Globs.fluorite.glob);
		biome = "extreme";
		registerWorldgenFromCountPerCube(minHeight, maxHeight, countPerCube, numberOfBlocks, ore, biome);

	}

}
