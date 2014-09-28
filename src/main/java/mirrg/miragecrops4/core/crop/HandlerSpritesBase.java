package mirrg.miragecrops4.core.crop;

import ic2.api.crops.ICropTile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HandlerSpritesBase implements IHandlerSprites
{

	protected final CropMirage cropMirage;

	@SideOnly(Side.CLIENT)
	protected IIcon textures[];

	@SideOnly(Side.CLIENT)
	protected boolean isRegistered = false;

	public HandlerSpritesBase(CropMirage cropMirage)
	{
		this.cropMirage = cropMirage;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerSprites(IIconRegister iconRegister)
	{
		if (isRegistered) return;
		isRegistered = true;

		textures = new IIcon[cropMirage.maxSize()];

		for (int i = 1; i <= textures.length; i++) {
			textures[i - 1] = iconRegister.registerIcon("ic2:crop/blockCrop." + cropMirage.name() + "." + i);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getSprite(ICropTile crop)
	{
		int size = crop.getSize();
		if (size <= 0) size = 1;
		if (size > textures.length) size = textures.length - 1;

		return textures[size - 1];
	}

}
