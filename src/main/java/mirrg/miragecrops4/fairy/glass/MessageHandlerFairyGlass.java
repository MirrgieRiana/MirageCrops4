package mirrg.miragecrops4.fairy.glass;

import mirrg.mir40.net.MessageFieldInt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerFairyGlass implements IMessageHandler<MessageFieldInt, IMessage>
{

	public static MessageFieldInt lastMessage;

	@Override
	public IMessage onMessage(MessageFieldInt arg0, MessageContext arg1)
	{
		lastMessage = arg0;
		return null;
	}

}
