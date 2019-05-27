package com.alleluid.principium.common.blocks.smelter

import com.alleluid.principium.common.BaseContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.SlotItemHandler

class ContainerSmelter(val player: EntityPlayer, val smelterTile: TileEntitySmelter) : BaseContainer(player.inventory, smelterTile) {
    val energy
        get() = smelterTile.energyStored

    init {
        val inventory = smelterTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)
        addSlotToContainer(object : SlotItemHandler(inventory, 0, 77, 20) {
            override fun onSlotChanged() {
                smelterTile.markDirty()
            }
        })
        addSlotToContainer(object : SlotItemHandler(inventory, 1, 77, 57) {
            override fun onSlotChanged() {
                smelterTile.markDirty()
            }
        })
        addSlotToContainer(object : SlotItemHandler(inventory, 2, 124, 38) {
            override fun onSlotChanged() {
                smelterTile.markDirty()
            }
        })

        playerInventorySetup(0, 0)

    }


    override fun canInteractWith(playerIn: EntityPlayer) = true
}