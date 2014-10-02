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

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack arg2)
	{
		if (world.isRemote) return;

		if (player instanceof EntityPlayerMP) {

			if (!lasttime.containsKey(player) || lasttime.get(player) + 20 < world.getWorldTime()) {
				lasttime.put(player, world.getWorldTime());

				ArrayList<int[]> data = new ArrayList<int[]>();

				{
					int centerX = (int) player.posX;
					int centerY = (int) player.posY;
					int centerZ = (int) player.posZ;

					for (int xi = -7; xi <= 7; xi++) {
						for (int yi = -3; yi <= 3; yi++) {
							for (int zi = -7; zi <= 7; zi++) {

								int currentX = centerX + xi;
								int currentY = centerY + yi;
								int currentZ = centerZ + zi;

								TileEntity te = player.worldObj.getTileEntity(
									currentX, currentY, currentZ);

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

											data.add(new int[] {
												currentX, currentY, currentZ, value,
											});

										}

									}
								}

							}
						}
					}

				}

				MessageDataViewInt message = new MessageDataViewInt();
				message.reset(data.size());
				for (int index = 0; index < data.size(); index++) {
					int[] datum = data.get(index);
					message.set(index, datum[0], datum[1], datum[2], datum[3]);
				}
				ModuleFairy.snw.sendTo(message, (EntityPlayerMP) player);

			}

		}

	}

}
