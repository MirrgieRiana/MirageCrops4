package mirrg.mir40.data.example;

import mirrg.mir40.data.NBTWrapperByte;
import mirrg.mir40.data.NBTWrapperCompound;
import mirrg.mir40.data.NBTWrapperFloat;
import mirrg.mir40.data.NBTWrapperInt;
import mirrg.mir40.data.NBTWrapperString;

/**
 * <pre>
 * hull : compound
 * +hull1 : hull(Hull1)
 *  +a : int
 *  +b : float
 *  +hull2 : hull(Hull2)
 *   +c : string
 *   +d : compoundtag
 *   +e : byte
 * </pre>
 */
public class NBTWrapperExample
{

	public static NBTWrapperExample.NBTWrapperHull1 nbtWrapper = new NBTWrapperHull1("hull");

	public static class NBTWrapperHull1 extends NBTWrapperCompound
	{

		public NBTWrapperHull1(String name)
		{
			super(name);
		}

		public NBTWrapperInt a = new NBTWrapperInt("a", this);
		public NBTWrapperFloat b = new NBTWrapperFloat("b", this);
		public NBTWrapperHull1.NBTWrapperHull2 hull2 = new NBTWrapperHull2("hull2", this);

		public static class NBTWrapperHull2 extends NBTWrapperCompound
		{

			public NBTWrapperHull2(String name, NBTWrapperCompound parent)
			{
				super(name, parent);
			}

			public NBTWrapperString c = new NBTWrapperString("c", this);
			public NBTWrapperCompound d = new NBTWrapperCompound("d", this);
			public NBTWrapperByte e = new NBTWrapperByte("e", this);

		}

	}

}
