package mirrg.mir34.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class HelperSide
{

	public final static HelperSide REMOTE = new HelperSide(true);
	public final static HelperSide SIMULATING = new HelperSide(false);

	public static HelperSide helper(Entity entity)
	{
		return helper(entity.worldObj);
	}

	public static HelperSide helper(TileEntity tileEntity)
	{
		return helper(tileEntity.getWorldObj());
	}

	public static HelperSide helper(World world)
	{
		return helper(world.isRemote);
	}

	public static HelperSide helper()
	{
		return helper(FMLCommonHandler.instance().getEffectiveSide().isClient());
	}

	public static HelperSide helper(boolean isRemote)
	{
		return isRemote ? REMOTE : SIMULATING;
	}

	public final boolean isRemote;

	public HelperSide(boolean isRemote)
	{
		this.isRemote = isRemote;
	}

	public boolean isRemote()
	{
		return isRemote;
	}

	public boolean isSimulating()
	{
		return !isRemote;
	}

	public boolean isRendering()
	{
		return isRemote;
	}

}
