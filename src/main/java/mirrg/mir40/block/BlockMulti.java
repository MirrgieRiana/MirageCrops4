package mirrg.mir40.block;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import mirrg.mir40.block.api.IMetablock;
import mirrg.mir40.multi.api.IMulti;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMulti<MULTI extends IMulti<MULTI, META>, META extends IMetablock<MULTI, META>>
	extends Block
{

	public final MULTI multi;

	public BlockMulti(Material material, Constructor<MULTI> constructorMulti, Object... argumentsConstructorMulti)
	{
		super(material);
		try {
			multi = constructorMulti.newInstance(argumentsConstructorMulti);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int damageDropped(int damage)
	{
		return damage;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, @SuppressWarnings("rawtypes") List list)
	{
		for (META meta : multi) {
			if (meta != null) {
				meta.getSubBlocks(item, creativeTabs, (List<ItemStack>) list);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		int metaId = blockAccess.getBlockMetadata(x, y, z);
		return multi.getMeta(metaId).getIcon(blockAccess, x, y, z, metaId);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metaId)
	{
		return multi.getMeta(metaId).getIcon(side, metaId);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		for (META meta : multi) {
			if (meta != null) {
				meta.registerBlockIcons(iconRegister);
			}
		}
	}

}
