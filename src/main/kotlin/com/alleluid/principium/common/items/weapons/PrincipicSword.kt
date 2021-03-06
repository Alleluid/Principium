package com.alleluid.principium.common.items.weapons

import com.alleluid.principium.common.items.BaseItem
import com.alleluid.principium.util.statusMessage
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand


object PrincipicSword : BaseItem("sword_principic") {
    init {
        loreText.addNamedKey("lore1") //The strong will be made weak, and the weak shall bow before me.
        infoText.addNamedKey("info1") //Damages to half a heart, but won't kill.

    }

    override fun onLeftClickEntity(stack: ItemStack, player: EntityPlayer, entity: Entity): Boolean {
        return false //logic moved to CommonEvents.kt
    }

    override fun itemInteractionForEntity(stack: ItemStack, playerIn: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean {
        statusMessage(playerIn, target.health.toString())
        return super.itemInteractionForEntity(stack, playerIn, target, hand)
    }
}