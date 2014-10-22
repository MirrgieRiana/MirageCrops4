package mirrg.miragecrops4.oregen.multi;

import mirrg.mir40.block.BlockMulti;
import mirrg.mir40.block.Metablock;
import mirrg.mir40.multi.Multibase;

public class BlockMultiMirageCrops<MULTI extends Multibase<MULTI, META>, META extends Metablock<MULTI, META>>
	extends BlockMulti<MULTI, META>
{

	@SuppressWarnings("rawtypes")
	public static class Raw extends BlockMultiMirageCrops
	{

	}

}
