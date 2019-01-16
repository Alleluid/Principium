package com.alleluid.principium.common.blocks

import com.alleluid.principium.MOD_ID
import com.alleluid.principium.common.blocks.box.BlockBox
import com.alleluid.principium.common.blocks.box.TileEntityBox
import com.alleluid.principium.common.blocks.counter.BlockCounter
import com.alleluid.principium.common.blocks.counter.TileEntityCounter
import com.alleluid.principium.common.blocks.pedestal.BlockPedestal
import com.alleluid.principium.common.blocks.pedestal.TileEntityPedestal
import com.alleluid.principium.common.blocks.smelter.BlockSmelter
import com.alleluid.principium.common.blocks.smelter.TileEntitySmelter
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.registries.IForgeRegistry
import com.alleluid.principium.Utils.Formatting as mcf

object ModBlocks {

    object blockPrincipic : BaseBlock(Material.GLASS, "block_principic") {
        init {
            lightValue = 15
            blockHardness = 0.3f
            blockSoundType = SoundType.GLASS
            setHarvestLevel("any", 10)
            initOreDict("blockPrincipic")
        }
    }

    object blockEgg : BaseBlock(Material.DRAGON_EGG, "block_egg") {
        init {
            loreText.add("¯\\_(ツ)_/¯")
        }

        override fun isOpaqueCube(state: IBlockState) = false
        override fun isFullBlock(state: IBlockState): Boolean = false
    }

    val blockMachineBase = BaseBlock(Material.IRON, "block_machine_base")


    @JvmStatic
    fun register(registry: IForgeRegistry<Block>) {
        registry.registerAll(
                blockMachineBase,
                blockEgg,
                blockPrincipic,
                FramedGlowBlock,
                BlockCounter,
                BlockPedestal,
                BlockSmelter,
                BlockPillow,
                BlockBox

        )

    }

    @JvmStatic
    fun registerItemBlocks(registry: IForgeRegistry<Item>) {
        registry.registerAll(
                blockMachineBase.createItemBlock(),
                blockEgg.createItemBlock(),
                blockPrincipic.createItemBlock(),
                FramedGlowBlock.createItemBlock(),
                BlockCounter.createItemBlock(),
                BlockPedestal.createItemBlock(),
                BlockSmelter.createItemBlock(),
                BlockPillow.createItemBlock(),
                BlockBox.createItemBlock()
        )
    }

    @JvmStatic
    fun registerModels() {
        blockMachineBase.registerItemModel()
        blockEgg.registerItemModel()
        blockPrincipic.registerItemModel()
        FramedGlowBlock.registerItemModel()
        BlockCounter.registerItemModel()
        BlockPedestal.registerItemModel()
        BlockSmelter.registerItemModel()
        BlockPillow.registerItemModel()
        BlockBox.registerItemModel()
    }

    @JvmStatic
    fun registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityCounter::class.java,
                ResourceLocation(MOD_ID, "tile_entity_counter"))

        TileEntityBox().registerTE()
        TileEntitySmelter().registerTE()
        TileEntityPedestal().registerTE()
    }
}