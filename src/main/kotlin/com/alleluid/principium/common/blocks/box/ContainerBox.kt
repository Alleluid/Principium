package com.alleluid.principium.common.blocks.box

import com.alleluid.principium.common.BaseContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Slot
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.SlotItemHandler
class ContainerBox(playerInv: InventoryPlayer, box: TileEntityBox) : BaseContainer(playerInv){

    init {
        playerInventorySetup(0, 37)
        val inventory = box.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)
        var count = 0
        for (i in 0 until 6){
            for (j in 0 until 9){
                addSlotToContainer(SlotItemHandler(inventory, count, 8 + j * 18, i * 18))
                count++
            }
        }
    }


    override fun canInteractWith(playerIn: EntityPlayer) = !playerIn.isSneaking

}

