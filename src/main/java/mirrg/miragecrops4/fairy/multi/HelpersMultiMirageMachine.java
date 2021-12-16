package mirrg.miragecrops4.fairy.multi;

import mirrg.mir40.nbt.EnumNBTTypes;
import mirrg.mir40.nbt.HelpersNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class HelpersMultiMirageMachine
{

	public static int getVirtualId(IBlockAccess blockAccess, int x, int y, int z)
	{
		TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);

		if (tileEntity instanceof ITileEntityMirageMachine) {
			return ((ITileEntityMirageMachine) tileEntity).getVirtualId();
		}

		return 0;
	}

	public static int getVirtualId(NBTTagCompound nbt)
	{
		if (HelpersNBT.hasKey(nbt, "virtualId", EnumNBTTypes.INT.ordinal())) {
			return HelpersNBT.get(nbt, "virtualId", HelpersNBT.INTEGER);
		}
		return 0;
	}

	public static void setVirtualId(NBTTagCompound nbt, int virtualId)
	{
		HelpersNBT.set(nbt, "virtualId", virtualId, HelpersNBT.INTEGER);
	}

}
