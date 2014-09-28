package mirrg.miragecrops4.core.crop;

import ic2.api.crops.CropCard;
import ic2.api.crops.ICropTile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HandlerSpritesWrapping extends HandlerSpritesBasic
{

	protected CropCard parent;
	protected int min;
	protected int max;

	public HandlerSpritesWrapping(
		CropMirage cropMirage, String mask, CropCard parent, int min, int max)
	{
		super(cropMirage, mask);
		this.parent = parent;
		this.min = min;
		this.max = max;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getSprite(ICropTile crop)
	{
		int size = crop.getSize();
		if (min <= size && size <= max) {
			return super.getSprite(crop);
		} else {
			return parent.getSprite(crop);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerSprites(IIconRegister iconRegister)
	{
		textures = new IIcon[cropMirage.maxSize()];

		for (int i = min; i <= max; i++) {
			textures[i - 1] = iconRegister.registerIcon(getSpriteName(i));
		}
	}

}
