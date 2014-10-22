package mirrg.miragecrops4.crops.fairy;

import ic2.api.crops.ICropTile;

public interface IFairyHirable
{

	/**
	 * サイズや状況的に職場が現在無効である場合falseとなる。タイルエンティティにタグが存在しない場合も無効となる。
	 */
	public boolean isHirable(ICropTile crop);

	/**
	 * 残り雇用可能人数。{@link IFairyHirable#isHirable} == trueである前提で呼び出される。
	 */
	public int getFairyCapacity(ICropTile crop);

	/**
	 * 指定された人数を雇用する。{@link IFairyHirable#getFairyCapacity} > 0である前提で呼び出される。
	 * 
	 * @param population
	 *            {@link IFairyHirable#getFairyCapacity}を超えない前提。
	 */
	public void hire(ICropTile crop, int population);

}
