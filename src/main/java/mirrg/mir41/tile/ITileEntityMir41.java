package mirrg.mir41.tile;

import mirrg.mir41.tile.inventory.IInventoryName;
import net.minecraft.tileentity.TileEntity;

public interface ITileEntityMir41 extends IInventoryName
{

	public TileEntity getTileEntity();

	public void onBroken();

}
