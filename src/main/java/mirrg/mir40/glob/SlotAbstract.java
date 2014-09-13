package mirrg.mir40.glob;

import mirrg.mir40.glob.api.ISlot;

public class SlotAbstract implements ISlot
{

	private String name;

	public SlotAbstract()
	{

	}

	public SlotAbstract(String name)
	{
		this.setName(name);
	}

	@Override
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
