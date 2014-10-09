package mirrg.miragecrops4.core;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ModuleMirageCropsBase extends ModuleAbstract
{

	public ModuleMirageCropsBase(IMod mod)
	{
		super(mod);
	}

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

}
