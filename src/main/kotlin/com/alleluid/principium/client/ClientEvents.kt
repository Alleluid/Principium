package com.alleluid.principium.client

import com.alleluid.principium.MOD_ID
import com.alleluid.principium.MineBlockMessage
import com.alleluid.principium.PacketHandler
import com.alleluid.principium.ShootMessage
import com.alleluid.principium.common.items.tools.LaserDrill
import com.alleluid.principium.common.items.weapons.BaseWeapon
import com.alleluid.principium.util.statusMessage
import net.minecraft.client.Minecraft
import net.minecraft.init.Items
import net.minecraft.util.EnumHand
import net.minecraftforge.client.event.MouseEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Mod.EventBusSubscriber(value = [Side.CLIENT], modid = MOD_ID)
object ClientHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun onMouseEvent(event: MouseEvent) {
        val mc = Minecraft.getMinecraft()
        val heldItem = mc.player.getHeldItem(EnumHand.MAIN_HAND).item
        if (heldItem != Items.AIR) {
            when (heldItem) {
                is BaseWeapon -> {
                    if (event.button == 0 && event.isButtonstate) {
                        val count = mc.player.inventory.getStackInSlot(0).count
                        statusMessage(mc.player, count.toString())
                        PacketHandler.INSTANCE.sendToServer(ShootMessage(count.toFloat()))
                        event.isCanceled = true
                    }
                }

                is LaserDrill -> {
                    if (event.button == 0 && event.isButtonstate
                            && mc.player.heldItemMainhand.tagCompound?.getBoolean("preciseMode") == true) {
                        PacketHandler.INSTANCE.sendToServer(MineBlockMessage())
                        event.isCanceled = true
                    }
                }
            }
        }

    }
}