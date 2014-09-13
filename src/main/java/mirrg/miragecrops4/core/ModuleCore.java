package mirrg.miragecrops4.core;

import mirrg.mir34.modding.IMod;
import mirrg.mir34.modding.ModuleAbstract;
import mirrg.miragecrops4.api.oregen.ItemsOregen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleCore extends ModuleAbstract
{

	public static CreativeTabs creativeTab;

	public ModuleCore(IMod mod)
	{
		super(mod);
	}

	@Override
	public void handle(FMLPreInitializationEvent event)
	{
		creativeTab = new CreativeTabs("miragecrops4") {

			@Override
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem()
			{
				return Item.getItemFromBlock(ItemsOregen.blockOreCalciteGroup);
			}

		};
	}

	@Override
	public String getModuleName()
	{
		return "core";
	}

}
