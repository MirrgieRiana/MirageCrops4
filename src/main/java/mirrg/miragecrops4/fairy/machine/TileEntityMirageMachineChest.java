package mirrg.miragecrops4.fairy.machine;

import mirrg.java8.function.IntUnaryOperator;
import mirrg.mir41.tile.ContainerMir41;
import mirrg.mir41.tile.inventory.FluidSlot;
import mirrg.mir41.tile.inventory.FluidTank;
import mirrg.mir41.tile.inventory.Inventory;
import mirrg.mir41.tile.inventory.InventoryChain;
import mirrg.mir41.tile.inventory.InventoryTrimmer;
import mirrg.miragecrops4.fairy.multi.TileEntityMirageMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;

public class TileEntityMirageMachineChest extends TileEntityMirageMachine
{

	public final Inventory inventory;

	public final FluidTank fluidTank;

	public TileEntityMirageMachineChest()
	{
		inventory = add(new Inventory(this, 9 * 3), "inventory");
		inventoryChain.add(inventory);

		fluidTank = add(new FluidTank(this, 16 * 1000), "fluidTank");
	}

	@Override
	protected Inventory[] getInventoryAccessible(int side)
	{
		return new Inventory[] {
			inventory,
		};
	}

	@Override
	protected Inventory[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
	}

	@Override
	protected Inventory[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
	}

	@Override
	protected ResourceLocation getGuiTexture(ContainerMir41 container)
	{
		return new ResourceLocation("miragecrops4" + ":" + "textures/gui/mirageMachine.png");
	}

	@Override
	protected boolean hasGui()
	{
		return true;
	}

	@Override
	protected void prepareContainerSlots(ContainerMir41 container)
	{
		InventoryChain inventoryChest = inventoryChain;
		InventoryTrimmer inventoryPlayer = new InventoryTrimmer(container.getPlayer().inventory, 9, 27);
		InventoryTrimmer inventoryHandle = new InventoryTrimmer(container.getPlayer().inventory, 0, 9);

		container.addInventory(inventoryChest, new IntUnaryOperator() {
			@Override
			public int applyAsInt(int id)
			{
				return 8 + (id % 9) * 18;
			}
		}, new IntUnaryOperator() {
			@Override
			public int applyAsInt(int id)
			{
				return 16 + (id / 9) * 18;
			}
		}, false);
		container.addInventory(inventoryPlayer, new IntUnaryOperator() {
			@Override
			public int applyAsInt(int id)
			{
				return 8 + (id % 9) * 18;
			}
		}, new IntUnaryOperator() {
			@Override
			public int applyAsInt(int id)
			{
				return 84 + (id / 9) * 18;
			}
		}, true);
		container.addInventory(inventoryHandle, new IntUnaryOperator() {
			@Override
			public int applyAsInt(int id)
			{
				return 8 + (id % 9) * 18;
			}
		}, new IntUnaryOperator() {
			@Override
			public int applyAsInt(int id)
			{
				return 142;
			}
		}, true);

		container.setTransferInventories(inventoryChest, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryPlayer, inventoryChest);
		container.setTransferInventories(inventoryHandle, inventoryChest);

		container.addFluidSlot(new FluidSlot(fluidTank, 180, 16, 16, 60));
	}

	@Override
	protected FluidTank[] getFluidTank(ForgeDirection arg0)
	{
		return new FluidTank[] {
			fluidTank
		};
	}

	@Override
	protected FluidTank getFluidTankDrain(ForgeDirection arg0)
	{
		return fluidTank;
	}

	@Override
	protected FluidTank getFluidTankDrain(ForgeDirection arg0, Fluid arg1)
	{
		return fluidTank;
	}

	@Override
	protected FluidTank getFluidTankFill(ForgeDirection arg0, Fluid arg1)
	{
		return fluidTank;
	}

}
