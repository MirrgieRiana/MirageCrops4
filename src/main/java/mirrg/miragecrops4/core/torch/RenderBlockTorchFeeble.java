package mirrg.miragecrops4.core.torch;

import static mirrg.miragecrops4.lib.oregen.ItemsOregen.*;
import mirrg.miragecrops4.lib.RenderBlockAbstract;
import mirrg.miragecrops4.lib.oregen.GlobsOregen;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
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
		if (modelId != this.getRenderId()) return;

		if (!(block instanceof BlockTorchFeeble)) return;

		GL11.glPushMatrix();

		{
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);

			float f;

			f = 0.05F;
			renderer.setRenderBounds(0.5F - f, 0.1F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
			renderCubeInInventory(block, metadata, renderer);

			BlockTorchFeeble btf = (BlockTorchFeeble) block;
			renderer.setOverrideBlockTexture(blockCalciteGroup.getIcon(0, 
				GlobsOregen.EnumGlobGroup.CalciteGroup.globGroup.indexOf(GlobsOregen.EnumGlob.calcite.glob)));

			f = 0.1f;
			renderer.setRenderBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.1F, 0.5F + f);
			renderCubeInInventory(block, metadata, renderer);

			renderer.clearOverrideBlockTexture();
		}

		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
		RenderBlocks renderer)
	{
		if (modelId != this.getRenderId()) return false;

		if (!(block instanceof BlockTorchFeeble)) return false;

		{
			float f;

			f = 0.05F;
			renderer.setRenderBounds(0.5F - f, 0.1F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
			renderer.renderStandardBlock(block, x, y, z);

			BlockTorchFeeble btf = (BlockTorchFeeble) block;
			renderer.setOverrideBlockTexture(blockCalciteGroup.getIcon(0, 
				GlobsOregen.EnumGlobGroup.CalciteGroup.globGroup.indexOf(GlobsOregen.EnumGlob.calcite.glob)));

			f = 0.1f;
			renderer.setRenderBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.1F, 0.5F + f);
			renderer.renderStandardBlock(block, x, y, z);

			renderer.clearOverrideBlockTexture();
		}

		return true;
	}

}
