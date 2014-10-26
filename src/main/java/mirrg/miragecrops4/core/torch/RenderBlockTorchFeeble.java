package mirrg.miragecrops4.core.torch;

import static mirrg.miragecrops4.oregen.global.ItemsOregen.*;
import mirrg.miragecrops4.core.RenderBlockAbstract;
import mirrg.miragecrops4.oregen.global.ItemsOregen.EnumGlobGroup;
import mirrg.miragecrops4.oregen.global.ItemsOregen.EnumGlob;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

public class RenderBlockTorchFeeble extends RenderBlockAbstract
{

	public RenderBlockTorchFeeble(int renderId)
	{
		super(renderId);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		if (modelId == this.getRenderId())
		{
			GL11.glPushMatrix();

			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);

			float f;

			f = 0.05F;
			renderer.setRenderBounds(0.5F - f, 0.1F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
			renderCube(block, metadata, renderer, Tessellator.instance);

			BlockTorchFeeble btf = (BlockTorchFeeble) block;
			btf.overridedIcon = blockCalciteGroup.getIcon(
				EnumGlobGroup.CalciteGroup.globGroup.getGlobs().indexOf(EnumGlob.calcite), 0);

			f = 0.1f;
			renderer.setRenderBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.1F, 0.5F + f);
			renderCube(block, metadata, renderer, Tessellator.instance);

			btf.overridedIcon = null;

			GL11.glPopMatrix();
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
		RenderBlocks renderer)
	{
		if (modelId == this.getRenderId())
		{
			float f;

			f = 0.05F;
			renderer.setRenderBounds(0.5F - f, 0.1F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
			renderer.renderStandardBlock(block, x, y, z);

			BlockTorchFeeble btf = (BlockTorchFeeble) block;
			btf.overridedIcon = blockCalciteGroup.getIcon(
				EnumGlobGroup.CalciteGroup.globGroup.getGlobs().indexOf(EnumGlob.calcite), 0);

			f = 0.1f;
			renderer.setRenderBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.1F, 0.5F + f);
			renderer.renderStandardBlock(block, x, y, z);

			btf.overridedIcon = null;

			return true;
		}
		return false;
	}

}
