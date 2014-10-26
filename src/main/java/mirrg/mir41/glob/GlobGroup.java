package mirrg.mir41.glob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class GlobGroup<GLOB extends IGlob> extends Base implements IGlobGroup<GLOB>
{

	protected ArrayList<GLOB> globs = new ArrayList<GLOB>();
	protected HashSet<String> allowsSlot = new HashSet<String>();

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

	public void setAllowsSlot(ISlot slot, boolean value)
	{
		if (value) {
			allowsSlot.add(slot.getName());
		} else {
			allowsSlot.remove(slot.getName());
		}
	}

	@Override
	public boolean allowsSlot(ISlot slot)
	{
		return allowsSlot.contains(slot.getName());
	}

}
