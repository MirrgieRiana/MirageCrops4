package mirrg.miragecrops4.fairy.glass;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mirrg.mir40.net.MessageFieldInt;
import mirrg.miragecrops4.crops.fairy.ICropDataView;
import mirrg.miragecrops4.fairy.ModuleFairy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFairyGlass extends ItemArmor
{

	private static Hashtable<EntityPlayer, Long> lasttime = new Hashtable<EntityPlayer, Long>();

	public ItemFairyGlass()
	{
		super(ItemArmor.ArmorMaterial.IRON, 3, 0);
	}

	public String armorTexture1;
	public String armorTexture2;

	@Override
	public String getArmorTexture(ItemStack arg0, Entity arg1, int arg2, String arg3)
	{
		return armorTexture1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack arg0, EntityPlayer arg1, List arg2, boolean arg3)
	{
		super.addInformation(arg0, arg1, arg2, arg3);

	}

	protected void addInt(List<int[]> dataList, IBlockAccess blockAccess, int x, int y, int z)
	{
		TileEntity te = blockAccess.getTileEntity(x, y, z);

		if (te != null) {
			if (te instanceof ICropTile) {
				ICropTile tec = (ICropTile) te;

				if (tec.getID() >= 0) {
					CropCard crop = Crops.instance.getCropList()[tec.getID()];
					int value;

					if (crop instanceof ICropDataView) {
						value = ((ICropDataView) crop).getDataView(tec);
					} else {
						value = 0; //crop.growthDuration(tec) - tec.growthPoints;
					}

					dataList.add(new int[] {
						x, y, z, value,
					});

				}

			}
		}

	}

	protected List<int[]> getFieldInt(IBlockAccess blockAccess, int x, int y, int z, int hMargin, int vMargin)
	{
		ArrayList<int[]> dataList = new ArrayList<int[]>();

		for (int xi = -hMargin; xi <= hMargin; xi++) {
			for (int yi = -vMargin; yi <= vMargin; yi++) {
				for (int zi = -hMargin; zi <= hMargin; zi++) {

					addInt(dataList, blockAccess, x + xi, y + yi, z + zi);

				}
			}
		}

		return dataList;
	}

	protected MessageFieldInt createMessage(List<int[]> dataList)
	{
		MessageFieldInt message = new MessageFieldInt();

		message.reset(dataList.size());

		for (int index = 0; index < dataList.size(); index++) {
			int[] datum = dataList.get(index);
			message.set(index, datum[0], datum[1], datum[2], datum[3]);
		}

		return message;
	}

	protected void onMessageTick(World world, EntityPlayer player, ItemStack arg2)
	{
		ModuleFairy.snw.sendTo(createMessage(
			getFieldInt(world, (int) player.posX, (int) player.posY, (int) player.posZ, 7, 3)),
			(EntityPlayerMP) player);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack arg2)
	{
		if (world.isRemote) return;

		if (player instanceof EntityPlayerMP) {

			if (!lasttime.containsKey(player) || lasttime.get(player) + 20 < world.getWorldTime()) {
				lasttime.put(player, world.getWorldTime());

				onMessageTick(world, player, arg2);

			}

		}

	}

}
