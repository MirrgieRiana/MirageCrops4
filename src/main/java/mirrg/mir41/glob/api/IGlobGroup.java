package mirrg.mir41.glob.api;

import java.util.List;

public interface IGlobGroup<GLOB extends IGlob>
{

	public String getName();

	public List<GLOB> getGlobs();

	public boolean allowsSlot(ISlot slot);

}
