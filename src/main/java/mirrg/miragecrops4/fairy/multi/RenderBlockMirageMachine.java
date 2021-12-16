package mirrg.miragecrops4.fairy.multi;

import mirrg.miragecrops4.lib.RenderBlockAbstract;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

public class RenderBlockMirageMachine extends RenderBlockAbstract
{

	public RenderBlockMirageMachine(int renderId)
	{
		super(renderId);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		if (modelId != this.getRenderId()) return;

		if (!(block instanceof BlockMultiMirageMachine)) return;

		GL11.glPushMatrix();

		{
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderCubeInInventoryMultiply((BlockMultiMirageMachine) block, metadata, renderer);
		}

		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z,
		Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId != this.getRenderId()) return false;

		if (!(block instanceof BlockMultiMirageMachine)) return false;

		{
			renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderStandardBlockMultiply(blockAccess, x, y, z, (BlockMultiMirageMachine) block, renderer);
		}

		return true;
	}

}
