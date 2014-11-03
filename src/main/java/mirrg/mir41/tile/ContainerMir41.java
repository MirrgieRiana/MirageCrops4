package mirrg.mir41.tile;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mirrg.java8.function.IntUnaryOperator;
import mirrg.mir41.tile.inventory.EnergySlot;
import mirrg.mir41.tile.inventory.FluidSlot;
import mirrg.mir41.tile.inventory.InventoryChain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerMir41 extends Container
{

	protected final EntityPlayer player;
	protected final ITileEntityMir41 tileEntity;
	protected final BlockPointer blockPointer;
	protected final ArrayList<FluidSlot> fluidSlots = new ArrayList<FluidSlot>();
	protected final ArrayList<EnergySlot> energySlots = new ArrayList<EnergySlot>();
	protected final InventoryChain inventoryChain = new InventoryChain(null, null);
	protected final Hashtable<Integer, int[]> transferInventoriesTable = new Hashtable<>();
	protected final Hashtable<Integer, Boolean> inventoryInverseTable = new Hashtable<>();

	public ContainerMir41(EntityPlayer player, ITileEntityMir41 tile, BlockPointer blockPointer)
	{
		this.player = player;
		this.tileEntity = tile;
		this.blockPointer = blockPointer;
	}

	public EntityPlayer getPlayer()
	{
		return player;
	}

	public ITileEntityMir41 getTileEntity()
	{
		return tileEntity;
	}

	public BlockPointer getBlockPointer()
	{
		return blockPointer;
	}

	@SuppressWarnings("unchecked")
	public List<Slot> getSlots()
	{
		return inventorySlots;
	}

	public List<FluidSlot> getFluidSlots()
	{
		return fluidSlots;
	}

	public List<EnergySlot> getEnergySlots()
	{
		return energySlots;
	}

	public void addInventory(IInventory inventory, IntUnaryOperator x, IntUnaryOperator y, boolean inverse)
	{
		inventoryChain.add(inventory);

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			super.addSlotToContainer(new Slot(inventory, i, x.applyAsInt(i), y.applyAsInt(i)));
		}

		inventoryInverseTable.put(inventoryChain.getInventoryCount() - 1, inverse);
	}

	/**
	 * @deprecated
	 * @see {@link #addInventory(IInventory)}
	 */
	@Deprecated
	@Override
	protected Slot addSlotToContainer(Slot p_addSlotToContainer_1_)
	{
		return super.addSlotToContainer(p_addSlotToContainer_1_);
	}

	public void addFluidSlot(FluidSlot fluidSlot)
	{
		fluidSlots.add(fluidSlot);
	}

	public void addEnergySlot(EnergySlot energySlot)
	{
		energySlots.add(energySlot);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		if (blockPointer.getTileEntity() != tileEntity) return false;
		return player.getDistanceSq(blockPointer.x + 0.5D, blockPointer.y + 0.5D, blockPointer.z + 0.5D) <= 8 * 8;
	}

	public void setTransferInventories(IInventory source, IInventory... destinations)
	{
		int[] destinationInventoryIndexes = new int[destinations.length];
		for (int i = 0; i < destinationInventoryIndexes.length; i++) {
			destinationInventoryIndexes[i] = inventoryChain.getInventoryIndex(destinations[i]);
		}

		setTransferInventories(inventoryChain.getInventoryIndex(source), destinationInventoryIndexes);
	}

	public void setTransferInventories(int sourceInventoryIndex, int... destinationInventoryIndexes)
	{
		transferInventoriesTable.put(sourceInventoryIndex, destinationInventoryIndexes);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2)
			{
				if (!this.mergeItemStack(itemstack1, 3, 39, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 != 1 && par2 != 0)
			{
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
				{
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
					{
						return null;
					}
				}
				else if (TileEntityFurnace.isItemFuel(itemstack1))
				{
					if (!this.mergeItemStack(itemstack1, 1, 2, false))
					{
						return null;
					}
				}
				else if (par2 >= 3 && par2 < 30)
				{
					if (!this.mergeItemStack(itemstack1, 30, 39, false))
					{
						return null;
					}
				}
				else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 3, 39, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

}
