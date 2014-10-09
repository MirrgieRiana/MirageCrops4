package mirrg.mir40.net;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class HelpersNet
{

	public static void sendChatToPlayer(EntityPlayer player, String string)
	{
		if (player instanceof EntityPlayerMP) {
			IChatComponent msg = new ChatComponentTranslation(string);
			((EntityPlayerMP) player).addChatMessage(msg);
		}
	}

}
