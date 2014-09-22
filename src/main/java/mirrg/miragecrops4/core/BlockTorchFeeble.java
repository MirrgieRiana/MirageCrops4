package mirrg.miragecrops4.core;

import net.minecraft.block.BlockTorch;

public class BlockTorchFeeble extends BlockTorch
{

	public void setLightLevel(int lightValue)
	{
		this.lightValue = lightValue;
	}

}