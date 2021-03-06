package com.alleluid.principium.util

import net.minecraft.entity.Entity
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.ai.attributes.IAttribute
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.Vec3d
import com.google.common.collect.Multimap
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextComponentTranslation
import java.util.*

fun statusMessage(player: EntityPlayer, key: String, vararg args: Any) {
    if (player.world.isRemote){
        player.sendStatusMessage(TextComponentTranslation(key, args), true)
    }
}

fun chatMessage(player: EntityPlayer, key: String, vararg args: Any) {
    if (player.world.isRemote){
        player.sendStatusMessage(TextComponentTranslation(key, args), false)
    }
}

@Deprecated("Replace with translation status!", ReplaceWith("statusMessage"))
fun noTranslateMessage(player: EntityPlayer, msg: String, isStatus: Boolean){
    if (player.world.isRemote){
        player.sendStatusMessage(TextComponentString(msg), isStatus)
    }
}

fun checkHeadspace(world: World, pos: BlockPos): Boolean {
    return world.isAirBlock(pos) && world.isAirBlock(pos.up())
}

fun particleGroup(world: World, type: EnumParticleTypes, posX: Double, posY: Double, posZ: Double, velocityMulti: Float = 1f) {
    for (i in 0..10) {
        world.spawnParticle(type, false,
                posX,
                posY,
                posZ,
                world.rand.nextGaussian() * velocityMulti,
                0.05,
                world.rand.nextGaussian() * velocityMulti
        )
    }
}

fun particleGroup(world: World, type: EnumParticleTypes, posX: Int, posY: Int, posZ: Int, velocityMulti: Float = 1f) {
    for (i in 0..10) {
        world.spawnParticle(type, false,
                posX + 0.5,
                posY.toDouble(),
                posZ + 0.5,
                world.rand.nextGaussian() * velocityMulti,
                0.05,
                world.rand.nextGaussian() * velocityMulti
        )
    }
}

fun particleGroup(world: World, type: EnumParticleTypes, entity: Entity, velocityMulti: Float = 1f) {
    particleGroup(
            world, type,
            entity.posX,
            entity.posY + entity.height - entity.height * 0.2,
            entity.posZ,
            velocityMulti
    )
}

fun World.spawnParticleVec(type: EnumParticleTypes, vec3d: Vec3d, posScalar: Double = 1.0, gaussianFactor: Double? = null) {
    val speed = if (gaussianFactor != null) {
        this.rand.nextGaussian() * gaussianFactor
    } else {
        0.0
    }
    this.spawnParticle(type, vec3d.x * posScalar, vec3d.y * posScalar, vec3d.z * posScalar, speed, speed, speed)
}

fun Entity.setPositionAndRotationAndUpdate(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
    this.setPositionAndUpdate(x, y, z)
    this.setPositionAndRotation(x, y, z, yaw, pitch)
}

fun Entity.setPositionAndRotationAndUpdate(x: Double, y: Double, z: Double) {
    this.setPositionAndRotationAndUpdate(x, y, z, this.rotationYaw, this.rotationPitch)
}

fun replaceModifer(modMap: Multimap<String, AttributeModifier>, attribute: IAttribute, id: UUID, multi: Double) {
    // Get modifers for specified attribute
    val modifiers: Collection<AttributeModifier> = modMap[attribute.name]

    // Find the modifer with the specified ID, if any
    val modiferOptional = modifiers.stream().filter { attributeModifier -> attributeModifier.id == id }.findFirst()

    if (modiferOptional.isPresent) { // If it exists;
        val modifer: AttributeModifier = modiferOptional.get()
        modifiers.minus(modifer) // Remove it
        // Add the new modifier
        modifiers.plus(AttributeModifier(modifer.id, modifer.name, modifer.amount * multi, modifer.operation))
    }
}

object Formatting {
    // Reference: https://minecraft.gamepedia.com/Formatting_codes
    // Colors
    const val BLACK = "§0"
    const val DARK_BLUE = "§1"
    const val DARK_GREEN = "§2"
    const val DARK_AQUA = "§3"
    const val DARK_RED = "§4"
    const val DARK_PURPLE = "§5"
    const val GOLD = "§6"
    const val GREY = "§7"
    const val DARK_GREY = "§8"
    const val BLUE = "§9"
    const val GREEN = "§a"
    const val AQUA = "§b"
    const val RED = "§c"
    const val LIGHT_PURPLE = "§d"
    const val YELLOW = "§e"
    const val WHITE = "§f"

    // Styles
    const val OBFUSCATED = "§k"
    const val BOLD = "§l"
    const val STRIKETHROUGH = "§m"
    const val UNDERLINE = "§n"
    const val ITALIC = "§o"
    const val RESET = "§r"

    const val LORE = DARK_PURPLE + ITALIC
}

