package mirrg.miragecrops4.lib;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir41.glob.Glob;
import mirrg.mir41.glob.GlobManager;
import mirrg.mir41.glob.HelpersGlob;
import mirrg.mir41.glob.IGlob;
import mirrg.mir41.glob.ISlot;
import mirrg.mir41.glob.Slot;
import mirrg.miragecrops4.oregen.global.GlobsOregen;
import mirrg.miragecrops4.oregen.global.GlobsOregen.EnumGlob;
import mirrg.miragecrops4.oregen.global.GlobsOregen.EnumSlot;
import mirrg.miragecrops4.oregen.global.ItemsOregen;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ModuleMirageCropsBase extends ModuleAbstract
{

	public ModuleMirageCropsBase(IMod mod)
	{
		super(mod);
	}

	//

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		register();

		configure();

	}

	@Override
	public void handle(FMLPostInitializationEvent event)
	{

		registerRecipes();

	}

	//

	protected void register()
	{

		registerBlocks();

		registerItems();

	}

	protected void configure()
	{

		configureBlocks();

		configureItems();

	}

	//

	protected void registerBlocks()
	{

	}

	protected void registerItems()
	{

	}

	protected void configureBlocks()
	{

	}

	protected void configureItems()
	{

	}

	protected void registerRecipes()
	{

	}

	//

	protected <T extends Block> T registerBlock(
		T block, Class<? extends ItemBlock> classItemBlock, String name)
	{
		GameRegistry.registerBlock(block, classItemBlock, name);
		return block;
	}

	protected void configureBlock(
		Block block, String name, float hardness, float resistance, SoundType stepSound)
	{
		block.setBlockName(name);
		block.setBlockTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
		block.setHardness(hardness);
		block.setResistance(resistance);
		block.setStepSound(stepSound);
	}

	protected <T extends Item> T registerItem(T item, String name)
	{
		GameRegistry.registerItem(item, name);
		return item;
	}

	protected void configureItem(Item item, String name)
	{
		item.setUnlocalizedName(name);
		item.setTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
	}

	/**
	 * {@link GlobManager#getDictionaryName(ISlot, IGlob)} の略
	 */
	protected String gdn(Slot slot, Glob glob)
	{
		return ItemsOregen.globManager.getDictionaryName(slot, glob);
	}

	/**
	 * {@link HelpersGlob#copy(mirrg.mir41.glob.api.IGlobManager, ISlot, IGlob)}
	 * の略
	 */
	protected ItemStack cpy(Slot slot, Glob glob)
	{
		return HelpersGlob.copy(ItemsOregen.globManager, slot, glob);
	}

	/**
	 * {@link HelpersGlob#copy(mirrg.mir41.glob.api.IGlobManager, ISlot, IGlob, int)}
	 * の略
	 */
	protected ItemStack cpy(Slot slot, Glob glob, int size)
	{
		return HelpersGlob.copy(ItemsOregen.globManager, slot, glob, size);
	}

	/**
	 * {@link GlobManager#getDictionaryName(ISlot, IGlob)} の略
	 */
	protected String gdn(GlobsOregen.EnumSlot enumSlot, GlobsOregen.EnumGlob enumGlob)
	{
		return gdn(enumSlot.slot, enumGlob.glob);
	}

	/**
	 * {@link HelpersGlob#copy(mirrg.mir41.glob.api.IGlobManager, ISlot, IGlob)}
	 * の略
	 */
	protected ItemStack cpy(GlobsOregen.EnumSlot enumSlot, GlobsOregen.EnumGlob enumGlob)
	{
		return cpy(enumSlot.slot, enumGlob.glob);
	}

	/**
	 * {@link HelpersGlob#copy(mirrg.mir41.glob.api.IGlobManager, ISlot, IGlob, int)}
	 * の略
	 */
	protected ItemStack cpy(GlobsOregen.EnumSlot enumSlot, GlobsOregen.EnumGlob enumGlob, int size)
	{
		return cpy(enumSlot.slot, enumGlob.glob, size);
	}

}
