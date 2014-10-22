package mirrg.miragecrops4.core;

import mirrg.mir34.modding.IMod;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import mirrg.miragecrops4.api.oregen.ItemsOregen.Globs;
import mirrg.miragecrops4.api.oregen.ItemsOregen.Slots;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
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

	//

	@Override
	public void handle(FMLPreInitializationEvent event)
	{

		prepareCreativeTabs();

		super.handle(event);

	}

	@SideOnly(Side.CLIENT)
	public static RenderBlockTorchFeeble renderBlockTorchFeeble;

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClient(FMLInitializationEvent event)
	{

		{
			int renderId = RenderingRegistry.getNextAvailableRenderId();
			renderBlockTorchFeeble = new RenderBlockTorchFeeble(renderId);
			RenderingRegistry.registerBlockHandler(renderId, renderBlockTorchFeeble);
		}

		super.handleClient(event);
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

	@Override
	protected void registerBlocks()
	{
		blockTorchFeeble = registerBlock(
			new BlockTorchFeeble(), ItemBlockTorchFeeble.class, "blockTorchFeeble");
	}

	@Override
	protected void configureBlocks()
	{

		configureBlock(blockTorchFeeble, "blockTorchFeeble", 0.15F, 0.0F, Block.soundTypeWood);
		blockTorchFeeble.setCreativeTab(creativeTab);

	}

	@Override
	protected void registerRecipes()
	{

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 12),
			"X",
			"Y",
			'X', gdn(Slots.gem, Globs.calcite),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 10),
			"X",
			"Y",
			'X', gdn(Slots.gem, Globs.magnesite),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 8),
			"X",
			"Y",
			'X', gdn(Slots.gem, Globs.smithsonite),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 6),
			"X",
			"Y",
			'X', gdn(Slots.gem, Globs.siderite),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 4),
			"X",
			"Y",
			'X', gdn(Slots.gem, Globs.sphaerocobaltite),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 3),
			"X",
			"Y",
			'X', gdn(Slots.gem, Globs.gaspeite),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 2),
			"X",
			"Y",
			'X', gdn(Slots.gem, Globs.rhodochrosite),
			'Y', Blocks.torch));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockTorchFeeble, 1, 1),
			"X",
			"Y",
			'X', gdn(Slots.gem, Globs.otavite),
			'Y', Blocks.torch));

	}

}
