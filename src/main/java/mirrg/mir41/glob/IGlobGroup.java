package mirrg.mir41.glob;

import java.util.List;

/**
 * キャメルケース大文字の名前
 */
public interface IGlobGroup<GLOB extends IGlob> extends Iterable<GLOB>
{

	public String getName();

	public List<GLOB> getGlobs();

	public boolean allowsSlot(ISlot slot);

	public int indexOf(GLOB glob);

}
