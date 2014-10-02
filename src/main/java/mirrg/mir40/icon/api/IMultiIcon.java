package mirrg.mir40.icon.api;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IMultiIcon extends IMultiIconShape
{

	@SideOnly(Side.CLIENT)
	public int getColor(int pass);

}
