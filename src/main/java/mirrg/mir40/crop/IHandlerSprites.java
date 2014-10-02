package mirrg.mir40.crop;

import ic2.api.crops.ICropTile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IHandlerSprites
{

	/**
	 * 複数回呼ばれる可能性あり
	 */
	@SideOnly(Side.CLIENT)
	public void registerSprites(IIconRegister iconRegister);

	@SideOnly(Side.CLIENT)
	public IIcon getSprite(ICropTile crop);

}
