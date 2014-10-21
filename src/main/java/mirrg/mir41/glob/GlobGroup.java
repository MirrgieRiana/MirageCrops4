package mirrg.mir41.glob;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mirrg.mir41.glob.api.IGlob;
import mirrg.mir41.glob.api.IGlobGroup;
import mirrg.mir41.glob.api.ISlot;

public class GlobGroup<GLOB extends IGlob> extends Base implements IGlobGroup<GLOB>
{

	protected ArrayList<GLOB> globs = new ArrayList<GLOB>();
	protected Hashtable<String, Boolean> allowsSlot = new Hashtable<String, Boolean>();

	public GlobGroup()
	{

	}

	public GlobGroup(String name)
	{
		super(name);
	}

	@Override
	public List<GLOB> getGlobs()
	{
		return globs;
	}

	public void registerGlob(GLOB glob)
	{
		if (globs.size() >= getCapacity()) {
			throw new RuntimeException("GlobGroup does not allow you to register the glob more than "
				+ getCapacity() + ": " + getName());
		}
		globs.add(glob);
	}

	protected int getCapacity()
	{
		return 16;
	}

	@Override
	public boolean allowsSlot(ISlot slot)
	{
		if (allowsSlot.containsKey(slot.getName())) {
			return allowsSlot.get(slot.getName());
		}
		return false;
	}

}
