package mirrg.miragecrops4.core.crop;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HandlerSpritesBasic extends HandlerSpritesBase
{

	protected String mask = "ic2:crop/blockCrop.%name%.%size%";

	public HandlerSpritesBasic(CropMirage cropMirage, String mask)
	{
		super(cropMirage);
		if (mask != null) setMask(mask);
	}

	public String getMask()
	{
		return mask;
	}

	public void setMask(String mask)
	{
		this.mask = mask;
	}

	protected String getSpriteName(int size)
	{
		String mask = getMask();
		mask = mask.replace("%name%", cropMirage.name());
		mask = mask.replace("%size%", String.valueOf(size));
		return mask;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerSprites(IIconRegister iconRegister)
	{
		textures = new IIcon[cropMirage.maxSize()];

		for (int i = 1; i <= textures.length; i++) {
			textures[i - 1] = iconRegister.registerIcon(getSpriteName(i));
		}
	}

}
