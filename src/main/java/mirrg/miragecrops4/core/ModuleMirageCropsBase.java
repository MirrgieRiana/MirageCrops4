package mirrg.miragecrops4.core;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ModuleMirageCropsBase extends ModuleAbstract
{

	public ModuleMirageCropsBase(IMod mod)
	{
		super(mod);
	}

	protected <B extends Block> B registerBlock(
		B block, Class<? extends ItemBlock> classItemBlock, String name)
	{
		GameRegistry.registerBlock(block, classItemBlock, name);
		return block;
	}

}
