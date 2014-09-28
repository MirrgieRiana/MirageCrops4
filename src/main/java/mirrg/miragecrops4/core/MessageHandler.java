package mirrg.miragecrops4.core;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandler implements IMessageHandler<MessageDataViewInt, IMessage>
{

	public static MessageDataViewInt lastMessage;

	@Override
	public IMessage onMessage(MessageDataViewInt arg0, MessageContext arg1)
	{
		lastMessage = arg0;
		return null;
	}

}