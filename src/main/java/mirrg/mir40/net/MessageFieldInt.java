package mirrg.mir40.net;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageFieldInt implements IMessage
{

	public int length;
	public int[] xs;
	public int[] ys;
	public int[] zs;
	public int[] values;

	public MessageFieldInt()
	{

	}

	public void reset(int length)
	{
		this.length = length;
		xs = new int[length];
		ys = new int[length];
		zs = new int[length];
		values = new int[length];
	}

	public void set(int index, int x, int y, int z, int value)
	{
		xs[index] = x;
		ys[index] = y;
		zs[index] = z;
		values[index] = value;
	}

	@Override
	public void fromBytes(ByteBuf arg0)
	{
		int length = arg0.readInt();

		reset(length);

		for (int index = 0; index < length; index++) {
			int x = arg0.readInt();
			int y = arg0.readInt();
			int z = arg0.readInt();
			int value = arg0.readInt();
			set(index, x, y, z, value);
		}

	}

	@Override
	public void toBytes(ByteBuf arg0)
	{

		arg0.writeInt(length);

		for (int index = 0; index < length; index++) {
			arg0.writeInt(xs[index]);
			arg0.writeInt(ys[index]);
			arg0.writeInt(zs[index]);
			arg0.writeInt(values[index]);
		}

	}

}
