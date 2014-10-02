package mirrg.miragecrops4.fairy.glass;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HandlerRenderingFairyGlass
{

	@SideOnly(Side.CLIENT)
	public void renderFairyGlassPopup(RenderWorldLastEvent event, EntityPlayer p,
		double px, double py, double pz, int x, int y, int z, int value)
	{

		GL11.glPushMatrix();
		{
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_TEXTURE_2D);

			GL11.glTranslated(-px, -py, -pz);

			{

				GL11.glPushMatrix();
				{
					GL11.glTranslated(x, y, z);
					GL11.glTranslatef(0.5f, 0.5f, 0.5f);

					GL11.glTranslatef(0, 1, 0);

					GL11.glScaled(0.02, 0.02, 1);

					String string = String.format("%s%d", EnumChatFormatting.BLUE, value);

					int width = Minecraft.getMinecraft().fontRenderer.getStringWidth(string);
					int height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

					GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);
					GL11.glScaled(-1, -1, 1);
					GL11.glTranslatef(-width * 0.5f, -height * 0.5f, 0);

					GL11.glTranslatef(width * 0.5f, height * 0.5f, 0);

					Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(string, 0, 0, 0);

				}
				GL11.glPopMatrix();
			}

			GL11.glAlphaFunc(516, 0.1F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		GL11.glPopMatrix();

	}

	@SideOnly(Side.CLIENT)
	public void renderFairyGlassVision(RenderWorldLastEvent event, EntityPlayer player, double px, double py, double pz)
	{
		if (MessageHandlerFairyGlass.lastMessage != null) {
			for (int index = 0; index < MessageHandlerFairyGlass.lastMessage.length; index++) {
				renderFairyGlassPopup(event, player, px, py, pz,
					MessageHandlerFairyGlass.lastMessage.xs[index],
					MessageHandlerFairyGlass.lastMessage.ys[index],
					MessageHandlerFairyGlass.lastMessage.zs[index],
					MessageHandlerFairyGlass.lastMessage.values[index]);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void onRenderLast(RenderWorldLastEvent event, EntityPlayer player)
	{
		ItemStack helmet = player.getCurrentArmor(3);

		if (helmet != null && helmet.getItem() instanceof ItemFairyGlass) {
			renderFairyGlassVision(event, player,
				player.prevPosX + (player.posX - player.prevPosX) * event.partialTicks,
				player.prevPosY + (player.posY - player.prevPosY) * event.partialTicks,
				player.prevPosZ + (player.posZ - player.prevPosZ) * event.partialTicks);
		}

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void handle(RenderWorldLastEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if ((Minecraft.getMinecraft().thePlayer instanceof EntityPlayer))
		{
			onRenderLast(event, (EntityPlayer) Minecraft.getMinecraft().thePlayer);
		}
	}

}
