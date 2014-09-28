package mirrg.miragecrops4.core;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.core.block.wiring.TileEntityElectricBatBox;
import ic2.core.crop.TileEntityCrop;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HandlerRendering
{

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderLast(RenderWorldLastEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if ((Minecraft.getMinecraft().thePlayer instanceof EntityPlayer))
		{
			EntityPlayer p = (EntityPlayer) Minecraft.getMinecraft().thePlayer;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_TEXTURE_2D);

			//UtilsFX.bindTexture(TileNodeRenderer.nodetex);

			{
				float size = 1;
				float alpha = 0.8f;

				//RenderCow.renderAABB(AxisAlignedBB.getBoundingBox(x, y, z, x + 10, y + 10, z + 10));

				float partialTicks = event.partialTicks;
				double iPX = p.prevPosX + (p.posX - p.prevPosX) * partialTicks;
				double iPY = p.prevPosY + (p.posY - p.prevPosY) * partialTicks;
				double iPZ = p.prevPosZ + (p.posZ - p.prevPosZ) * partialTicks;
				GL11.glTranslated(-iPX, -iPY, -iPZ);

				/*
				float xd = (float) (iPX - (x + 0.5D));
				float zd = (float) (iPZ - (z + 0.5D));
				float rotYaw = (float) (Math.atan2(xd, zd) * 180.0D / 3.141592653589793D);

				GL11.glRotatef(rotYaw + 180.0F, 0.0F, 1.0F, 0.0F);
				  GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
				  GL11.glScalef(0.02F, 0.02F, 0.02F);
				  */

				long ms = System.currentTimeMillis();

				float motion = (ms % 1000) * 0.001f;

				{
					List tileEntities = event.context.tileEntities;

					for (Object obj : tileEntities) {
						if (obj != null) {
							if (obj instanceof TileEntity) {
								TileEntity te = (TileEntity) obj;

								//System.out.println("" + te.xCoord + " " + te.yCoord + " " + te.zCoord + "   " + te.getClass());



							}
						}
					}

				}

				if (MessageHandler.lastMessage != null) {
					for (int index = 0; index < MessageHandler.lastMessage.length; index++) {
						int x = MessageHandler.lastMessage.xs[index];
						int y = MessageHandler.lastMessage.ys[index];
						int z = MessageHandler.lastMessage.zs[index];
						int value = MessageHandler.lastMessage.values[index];

						{
							GL11.glPushMatrix();

							GL11.glTranslated(x, y, z);
							GL11.glTranslatef(0.5f, 0.5f, 0.5f);

							GL11.glTranslatef(0, 1, 0);

							GL11.glScaled(0.02, 0.02, 1);

							int i = value;

							String string = "" + EnumChatFormatting.BLUE + i;
							int width = Minecraft.getMinecraft().fontRenderer
								.getStringWidth(string);
							int height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

							GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);
							GL11.glScaled(-1, -1, 1);
							GL11.glTranslatef(-width * 0.5f, -height * 0.5f, 0);

							GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);

							//GL11.glTranslated(0, 5, 0);

							//GL11.glColor4f(1.0F, 0.5F, 1.0F, 1.0F);
							//GL11.glEnable(GL11.GL_LIGHTING);
							Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(string, 0, 0, 0);
							//GL11.glDisable(GL11.GL_LIGHTING);

							GL11.glPopMatrix();
						}

					}
				}

				if (false) {
					int centerX = (int) p.posX;
					int centerY = (int) p.posY;
					int centerZ = (int) p.posZ;

					for (int xi = -7; xi <= 7; xi++) {
						for (int yi = -3; yi <= 3; yi++) {
							for (int zi = -7; zi <= 7; zi++) {

								int currentX = centerX + xi;
								int currentY = centerY + yi;
								int currentZ = centerZ + zi;

								TileEntity te = p.worldObj.getTileEntity(
									currentX, currentY, currentZ);

								if (te != null) {
									if (te instanceof TileEntityFurnace) {
										TileEntityFurnace tef = (TileEntityFurnace) te;

										{
											//GL11.glRotatef(motion * 360, 0, 1, 0);

											/*
											{
												GL11.glPushMatrix();

												GL11.glTranslated(x, y, z);
												GL11.glScaled(1, 1, 1);

												Tessellator tessellator = Tessellator.instance;
												tessellator.startDrawingQuads();
												//tessellator.setNormal(0, 0, -1);
												tessellator.setColorRGBA(255, 255, 255, 255);
												tessellator.addVertexWithUV(0, 0, z, 0, 0);
												tessellator.addVertexWithUV(0, 1, z, 0, 1);
												tessellator.addVertexWithUV(1, 1, z, 1, 1);
												tessellator.addVertexWithUV(1, 0, z, 1, 0);
												tessellator.draw();

												GL11.glPopMatrix();
											}
											*/

											{
												GL11.glPushMatrix();

												GL11.glTranslated(currentX, currentY, currentZ);
												GL11.glTranslatef(0.5f, 0.5f, 0.5f);

												GL11.glTranslatef(0, 1, 0);

												GL11.glScaled(0.02, 0.02, 1);

												String string = "" + ((int) (tef.isBurning() ? 50 : 90)) + " / 200";
												int width = Minecraft.getMinecraft().fontRenderer
													.getStringWidth(string);
												int height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

												GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);
												GL11.glScaled(-1, -1, 1);
												GL11.glTranslatef(-width * 0.5f, -height * 0.5f, 0);

												GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);

												//GL11.glTranslated(0, 5, 0);

												Minecraft.getMinecraft().fontRenderer.drawString(string, 0, 0, 0);

												GL11.glPopMatrix();
											}

										}

									}
									if (te instanceof TileEntityChest) {
										TileEntityChest tec = (TileEntityChest) te;

										{

											{
												GL11.glPushMatrix();

												GL11.glTranslated(currentX, currentY, currentZ);
												GL11.glTranslatef(0.5f, 0.5f, 0.5f);

												GL11.glTranslatef(0, 1, 0);

												GL11.glScaled(0.02, 0.02, 1);

												int i = 0;
												{
													ItemStack is = tec.getStackInSlot(0);
													if (is != null) {
														i = is.stackSize;
													}
												}

												String string = "" + ((int) (i)) + " / 200";
												int width = Minecraft.getMinecraft().fontRenderer
													.getStringWidth(string);
												int height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

												GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);
												GL11.glScaled(-1, -1, 1);
												GL11.glTranslatef(-width * 0.5f, -height * 0.5f, 0);

												GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);

												//GL11.glTranslated(0, 5, 0);

												Minecraft.getMinecraft().fontRenderer.drawString(string, 0, 0, 0);

												GL11.glPopMatrix();
											}

										}

									}
									if (te instanceof TileEntityElectricBatBox) {
										TileEntityElectricBatBox teebb = (TileEntityElectricBatBox) te;

										{

											{
												GL11.glPushMatrix();

												GL11.glTranslated(currentX, currentY, currentZ);
												GL11.glTranslatef(0.5f, 0.5f, 0.5f);

												GL11.glTranslatef(0, 1, 0);

												GL11.glScaled(0.02, 0.02, 1);

												int i = 0;
												{
													i = (int) teebb.energy;
												}

												String string = "" + ((int) (i)) + " / 200";
												int width = Minecraft.getMinecraft().fontRenderer
													.getStringWidth(string);
												int height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

												GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);
												GL11.glScaled(-1, -1, 1);
												GL11.glTranslatef(-width * 0.5f, -height * 0.5f, 0);

												GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);

												//GL11.glTranslated(0, 5, 0);

												Minecraft.getMinecraft().fontRenderer.drawString(string, 0, 0, 0);

												GL11.glPopMatrix();
											}

										}

									}
									if (te instanceof TileEntityCrop) {
										TileEntityCrop tec = (TileEntityCrop) te;

										{

											{
												GL11.glPushMatrix();

												GL11.glTranslated(currentX, currentY, currentZ);
												GL11.glTranslatef(0.5f, 0.5f, 0.5f);

												GL11.glTranslatef(0, 1, 0);

												GL11.glScaled(0.02, 0.02, 1);

												int i = 0;
												int ii = 0;
												{
													i = (int) tec.growthPoints;

													ii = (int) tec.id;
													if (ii >= 0) {
														CropCard crop = Crops.instance.getCropList()[ii];
														ii = crop.growthDuration(tec);
													}
												}

												String string = "" + EnumChatFormatting.BLUE + i + " / " + ii;
												int width = Minecraft.getMinecraft().fontRenderer
													.getStringWidth(string);
												int height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

												GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);
												GL11.glScaled(-1, -1, 1);
												GL11.glTranslatef(-width * 0.5f, -height * 0.5f, 0);

												GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);

												//GL11.glTranslated(0, 5, 0);

												//GL11.glColor4f(1.0F, 0.5F, 1.0F, 1.0F);
												//GL11.glEnable(GL11.GL_LIGHTING);
												Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(string, 0, 0, 0);
												//GL11.glDisable(GL11.GL_LIGHTING);

												GL11.glPopMatrix();
											}

										}

									}
								}

							}
						}
					}

				}

				{
				}

				/*
				GL11.glPushMatrix();
				UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.2F * size, alpha, 32, 0, cframe,
					partialTicks, 11184657);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.5F * size, alpha, 32, 0, cframe,
					partialTicks, 11145506);
				GL11.glPopMatrix();
				*/
			}

			GL11.glAlphaFunc(516, 0.1F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_TEXTURE_2D);

			GL11.glPopMatrix();
		}
	}

}