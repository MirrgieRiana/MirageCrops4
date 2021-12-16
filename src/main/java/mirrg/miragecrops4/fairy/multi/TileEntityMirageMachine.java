package mirrg.miragecrops4.fairy.multi;

import mirrg.mir41.tile.TileEntityMir41Connected;
import mirrg.miragecrops4.fairy.ModuleFairy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntityMirageMachine extends TileEntityMir41Connected implements ITileEntityMirageMachine
{

	public int virtualId;

	@Override
	public int getVirtualId()
	{
		return virtualId;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		virtualId = HelpersMultiMirageMachine.getVirtualId(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		HelpersMultiMirageMachine.setVirtualId(nbt, virtualId);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
		EntityPlayer player, int side, float x2, float y2, float z2)
	{
		if (hasGui()) {
			if (!world.isRemote) {
				player.openGui(ModuleFairy.guiHandler.iMod, ModuleFairy.guiHandler.guiId, world, x, y, z);
			}

			return true;
		}

		return false;
	}
}
