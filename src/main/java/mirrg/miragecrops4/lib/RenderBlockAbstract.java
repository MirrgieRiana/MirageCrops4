package mirrg.miragecrops4.lib;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderBlockAbstract implements ISimpleBlockRenderingHandler
{

	private final int renderId;

	public RenderBlockAbstract(int renderId)
	{
		this.renderId = renderId;
	}

	public static <T extends Block & IBlockMultipleRendering>
		void renderStandardBlockMultiply(IBlockAccess blockAccess, int x, int y, int z,
			T block, RenderBlocks renderer)
	{
		HelpersBlockMultipleRendering.setMultipleRendering(block, true);
		for (int i = 0; i < HelpersBlockMultipleRendering.getMultipleRenderPasses(block, blockAccess, x, y, z); i++) {
			block.setMultipleRenderPass(i);
			renderer.renderStandardBlock(block, x, y, z);
		}
		HelpersBlockMultipleRendering.setMultipleRendering(block, false);
	}

	public static <T extends Block & IBlockMultipleRendering>
		void renderCubeInInventoryMultiply(T block, int metadata, RenderBlocks renderer)
	{
		float r;
		float g;
		float b;
		float a;
		{
			ByteBuffer bb = ByteBuffer.allocateDirect(4 * 16);
			bb.order(ByteOrder.nativeOrder());
			FloatBuffer fb = bb.asFloatBuffer();
			GL11.glGetFloat(GL11.GL_CURRENT_COLOR, fb);
			r = fb.get(0);
			g = fb.get(1);
			b = fb.get(2);
			a = fb.get(3);
		}

		GL11.glPushAttrib(8192);
		//GL11.glEnable(GL11.GL_TEXTURE_2D);
		//GL11.glEnable(GL11.GL_ALPHA_TEST);
		//GL11.glDisable(GL11.GL_BLEND);
		//GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		//GL11.glEnable(GL11.GL_BLEND);
		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		/*TODO
		 GL_ZERO	(0,0,0,0)
		GL_ONE	(1,1,1,1)
		GL_SRC_COLOR	(Rs/kR ,Gs/kG,Bs/kB,As/kA)
		GL_ONE_MINUS_SRC_COLOR	(1,1,1,1)-(Rs/kR,Gs/kG,Bs/kB,As/kA)
		GL_DST_COLOR	(Rd/kR,Gd/kG,Bd/kB,Ad/kA)
		GL_ONE_MINUS_DST_COLOR	(1,1,1,1)-(Rd/kR,Gd/kG,Bd/kB,Ad/kA)
		GL_SRC_ALPHA	(As/kA,As/kA,As/kA,As/kA)
		GL_ONE_MINUS_SRC_ALPHA	(1,1,1,1)-(As/kA,As/kA,As/kA,As/kA)
		GL_DST_ALPHA	(Ad/kA,Ad/kA,Ad/kA,Ad/kA)
		GL_ONE_MINUS_DST_ALPHA	(1,1,1,1)-(Ad/kA,Ad/kA,Ad/kA,Ad/kA)
		GL_SRC_ALPHA_SATURATE	(i,i,i,1)
		 */
		HelpersBlockMultipleRendering.setMultipleRendering(block, true);
		for (int i = 0; i < HelpersBlockMultipleRendering.getMultipleRenderPasses(block, metadata); i++) {

			int color = HelpersBlockMultipleRendering.getMultipleRenderColor(block, metadata, i);

			//GL11.glColor4f(1, 1, 1, 1);
			/*
			GL11.glColor4f(
				r * ((color >> 16) & 0xff) / 255.0f,
				g * ((color >> 8) & 0xff) / 255.0f,
				b * (color & 0xff) / 255.0f,
				a);
				*/

			//Tessellator.instance.setColorOpaque_F(((color >> 16) & 0xff) / 255.0f, ((color >> 8) & 0xff) / 255.0f, (color & 0xff) / 255.0f);

			renderCubeInInventory(block, metadata, renderer, color);
		}
		HelpersBlockMultipleRendering.setMultipleRendering(block, false);

		GL11.glColor4f(r, g, b, a);

		GL11.glPopAttrib();
	}

	public static void renderCubeInInventory(Block block, int metadata, RenderBlocks renderer)
	{
		renderCubeInInventory(block, metadata, renderer, 0xffffff);
	}
	
	public static void renderCubeInInventory(Block block, int metadata, RenderBlocks renderer, int color)
	{
		Tessellator t = Tessellator.instance;

		t.startDrawingQuads();
		t.setNormal(0.0F, -1.0F, 0.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, -1.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, 1.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(-1.0F, 0.0F, 0.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(1.0F, 0.0F, 0.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		t.draw();

	}

	@Override
	public int getRenderId()
	{
		return renderId;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public abstract void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer);

	@Override
	public abstract boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z,
		Block block, int modelId, RenderBlocks renderer);

}
