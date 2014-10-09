package mirrg.mir40.crop;

import ic2.api.crops.CropCard;
import ic2.api.crops.ICropTile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CropMirage extends CropCard
{

	// regular

	private String name = "No Name Crop";
	private int tier = 0;
	private String discoveredBy = "Mirrgie Riana";

	public void setName(String name)
	{
		this.name = name;
	}

	public void setStatusRegular(int tier, String discoveredBy)
	{
		this.tier = tier;
		this.discoveredBy = discoveredBy;
	}

	@Override
	public String name()
	{
		return name;
	}

	@Override
	public int tier()
	{
		return tier;
	}

	@Override
	public String discoveredBy()
	{
		return discoveredBy;
	}

	// crossing

	private int[] stat = new int[5];
	private String[] attributes = new String[0];

	public void setStatusCrossing(int chemical, int food, int defensive, int color, int weed,
		String... attributes)
	{
		stat[0] = chemical;
		stat[1] = food;
		stat[2] = defensive;
		stat[3] = color;
		stat[4] = weed;
		this.attributes = attributes;
	}

	@Override
	public int stat(int n)
	{
		return stat[n];
	}

	@Override
	public String[] attributes()
	{
		return attributes;
	}

	private IHandlerCross handlerCross = new HandlerCrossBase(this);

	public void setHandlerCross(IHandlerCross handlerCross)
	{
		this.handlerCross = handlerCross;
	}

	@Override
	public boolean canCross(ICropTile crop)
	{
		return handlerCross.canCross(crop);
	}

	// size, texture

	private int maxSize = 1;

	public void setMaxSize(int maxSize)
	{
		this.maxSize = maxSize;
	}

	@Override
	public int maxSize()
	{
		return maxSize;
	}

	private IHandlerSprites handlerSprites = new HandlerSpritesBase(this);

	public void setHandlerSprites(IHandlerSprites handlerSprites)
	{
		this.handlerSprites = handlerSprites;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerSprites(IIconRegister iconRegister)
	{
		handlerSprites.registerSprites(iconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getSprite(ICropTile crop)
	{
		return handlerSprites.getSprite(crop);
	}

	// grow

	private IHandlerGrow handlerGrow = new HandlerGrowBase(this);

	public void setHandlerGrow(IHandlerGrow handlerGrow)
	{
		this.handlerGrow = handlerGrow;
	}

	@Override
	public boolean canGrow(ICropTile crop)
	{
		return handlerGrow.canGrow(crop);
	}

	@Override
	public int growthDuration(ICropTile crop)
	{
		return handlerGrow.growthDuration(crop);
	}

	@Override
	public int getrootslength(ICropTile crop)
	{
		return handlerGrow.getrootslength(crop);
	}

	@Override
	public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air)
	{
		return handlerGrow.weightInfluences(crop, humidity, nutrients, air);
	}

	// seed

	private IHandlerSeed handlerSeed = new HandlerSeedBasic(this);

	public void setHandlerSeed(IHandlerSeed handlerSeed)
	{
		this.handlerSeed = handlerSeed;
	}

	@Override
	public ItemStack getSeeds(ICropTile crop)
	{
		return handlerSeed.getSeeds(crop);
	}

	@Override
	public float dropSeedChance(ICropTile crop)
	{
		return handlerSeed.dropSeedChance(crop);
	}

	// harvest

	private IHandlerHarvest handlerHarvest = new HandlerHarvestBasic(this);

	public void setHandlerHarvest(IHandlerHarvest handlerHarvest)
	{
		this.handlerHarvest = handlerHarvest;
	}

	@Override
	public ItemStack getGain(ICropTile crop)
	{
		return handlerHarvest.getGain(crop);
	}

	@Override
	public float dropGainChance()
	{
		return handlerHarvest.dropGainChance();
	}

	@Override
	public int getOptimalHavestSize(ICropTile crop)
	{
		return handlerHarvest.getOptimalHavestSize(crop);
	}

	@Override
	public boolean canBeHarvested(ICropTile crop)
	{
		return handlerHarvest.canBeHarvested(crop);
	}

	@Override
	public byte getSizeAfterHarvest(ICropTile crop)
	{
		return handlerHarvest.getSizeAfterHarvest(crop);
	}

}
