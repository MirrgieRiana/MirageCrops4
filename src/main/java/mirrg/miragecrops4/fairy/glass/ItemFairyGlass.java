package mirrg.miragecrops4.fairy.glass;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.core.crop.TileEntityCrop;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mirrg.miragecrops4.core.fairy.crop.ICropDataView;
import mirrg.miragecrops4.fairy.ModuleFairy;
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack arg0, EntityPlayer arg1, List arg2, boolean arg3)
	{
		super.addInformation(arg0, arg1, arg2, arg3);

	}

	protected void addIntData(List<int[]> dataList, IBlockAccess blockAccess, int x, int y, int z)
	{
		TileEntity te = blockAccess.getTileEntity(x, y, z);

		if (te != null) {
			if (te instanceof TileEntityCrop) {
				TileEntityCrop tec = (TileEntityCrop) te;

				if (tec.id >= 0) {
					CropCard crop = Crops.instance.getCropList()[tec.id];
					int value;

					if (crop instanceof ICropDataView) {
						value = ((ICropDataView) crop).getDataView(tec);
					} else {
						value = crop.growthDuration(tec) - tec.growthPoints;
					}

					dataList.add(new int[] {
						x, y, z, value,
					});

				}

			}
		}

	}

	protected List<int[]> getIntData(IBlockAccess blockAccess, int x, int y, int z, int hMargin, int vMargin)
	{
		ArrayList<int[]> dataList = new ArrayList<int[]>();

		for (int xi = -hMargin; xi <= hMargin; xi++) {
			for (int yi = -vMargin; yi <= vMargin; yi++) {
				for (int zi = -hMargin; zi <= hMargin; zi++) {

					addIntData(dataList, blockAccess, x + xi, y + yi, z + zi);

				}
			}
		}

		return dataList;
	}

	protected MessageDataViewInt createMessage(List<int[]> dataList)
	{
		MessageDataViewInt message = new MessageDataViewInt();

		message.reset(dataList.size());

		for (int index = 0; index < dataList.size(); index++) {
			int[] datum = dataList.get(index);
			message.set(index, datum[0], datum[1], datum[2], datum[3]);
		}

		return message;
	}

	protected void onMessageTick(World world, EntityPlayer player, ItemStack arg2)
	{
		ModuleFairy.snw.sendTo(
			createMessage(
			getIntData(world, (int) player.posX, (int) player.posY, (int) player.posZ, 7, 3)),
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