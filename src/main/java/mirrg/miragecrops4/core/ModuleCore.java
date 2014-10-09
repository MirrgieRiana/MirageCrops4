package mirrg.miragecrops4.core;

import static mirrg.miragecrops4.api.oregen.ItemsOregen.*;
import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleCore extends ModuleAbstract
{

	public ModuleCore(IMod mod)
	{
		super(mod);
	}

	@Override
	public String getModuleName()
	{
		return "core";
	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		prepareCreativeTabs();

		registerBlocks();

	}

	@Override
	public void handle(FMLPostInitializationEvent event)
	{

		registerRecipes();

	}

	//

	public static CreativeTabs creativeTab;

	public static Block blockTorchFeeble;

	private void prepareCreativeTabs()
	{

		creativeTab = new CreativeTabs("miragecrops4") {

			@Override
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem()
			{
				return Item.getItemFromBlock(ItemsOregen.blockOreCalciteGroup);
			}

		};

	}

	private void registerBlocks()
	{

		{
			String name = "blockTorchFeeble";
			BlockTorchFeeble block = new BlockTorchFeeble();
			block.setHardness(0.0F);
			block.setLightLevel(12);
			block.setStepSound(Block.soundTypeWood);
			block.setBlockName(name);
			block.setBlockTextureName(getMod().getModId() + ":" + getModuleName() + "/" + name);
			block.setCreativeTab(creativeTab);
			GameRegistry.registerBlock(block, name);
			blockTorchFeeble = block;
		}

	}

	private void registerRecipes()
	{

		GameRegistry.addRecipe(new ShapedOreRecipe(blockTorchFeeble,
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.calcite.glob),
			'Y', Blocks.torch));

	}

}
