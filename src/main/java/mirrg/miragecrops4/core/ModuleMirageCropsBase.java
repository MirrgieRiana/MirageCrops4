package mirrg.miragecrops4.core;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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

}
