package mirrg.miragecrops4.oregen.multi;

import mirrg.mir40.block.BlockMulti;
import mirrg.mir40.block.Metablock;
import mirrg.mir40.multi.Multibase;
import net.minecraft.block.material.Material;

public class BlockMultiMirageCrops<MULTI extends Multibase<MULTI, META>, META extends Metablock<MULTI, META>>
	extends BlockMulti<MULTI, META>
{

	public BlockMultiMirageCrops(Material material)
	{
		super(material);
	}

	@SuppressWarnings("rawtypes")
	public static class Raw extends BlockMultiMirageCrops
	{

		public Raw(Material material)
		{
			super(material);
		}

	}

}
