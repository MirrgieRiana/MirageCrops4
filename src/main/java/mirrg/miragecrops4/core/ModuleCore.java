package mirrg.miragecrops4.core;

import static mirrg.miragecrops4.api.oregen.ItemsOregen.*;
import mirrg.mir34.modding.IMod;
import mirrg.mir40.glob.api.HelpersGlob;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.EnumGlobsCalciteGroup;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleCore extends ModuleMirageCropsBase
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

		configureBlocks();

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
		blockTorchFeeble = registerBlock(
			new BlockTorchFeeble(), ItemBlockTorchFeeble.class, "blockTorchFeeble");
	}

	private void configureBlocks()
	{

		configureBlock(blockTorchFeeble, "blockTorchFeeble", 0.0F, 0.0F, Block.soundTypeWood);
		blockTorchFeeble.setCreativeTab(creativeTab);

	}

	private void registerRecipes()
	{

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 12),
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.calcite.glob),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 10),
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.magnesite.glob),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 8),
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.smithsonite.glob),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 6),
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.siderite.glob),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 4),
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.sphaerocobaltite.glob),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 3),
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.gaspeite.glob),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 2),
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.rhodochrosite.glob),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 1),
			"X",
			"Y",
			'X', HelpersGlob.getDictionaryName(slotOre, EnumGlobsCalciteGroup.otavite.glob),
			'Y', Blocks.torch));

	}

}
