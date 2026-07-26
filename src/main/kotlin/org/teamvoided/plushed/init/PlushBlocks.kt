package org.teamvoided.plushed.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.block.PlushieBlock
import org.teamvoided.plushed.util.getModEntries
import org.teamvoided.plushed.util.key
import org.teamvoided.plushed.util.register
import java.util.function.Function

object PlushBlocks {

    val BLOCKS get() = getModEntries(BuiltInRegistries.BLOCK)

    val PLUSHIE_BLOCK = registerNoItem("plushie_block", ::PlushieBlock, ofFullCopy(Blocks.WHITE_WOOL))

    fun init() = Unit

    // region register
   /* fun register(
        id: String,
        blockCreator: Function<BlockBehaviour.Properties, Block>,
        properties: BlockBehaviour.Properties,
        itemProps: Item.Properties = Item.Properties(),
    ): Block {
        val registeredBlock = registerNoItem(id, blockCreator, properties)
        PlushItems.register(id, { p -> BlockItem(registeredBlock, p) }, itemProps)
        return registeredBlock
    }

    fun registerNoItem(name: String, properties: BlockBehaviour.Properties): Block =
        registerNoItem(name, ::Block, properties)*/

    fun registerNoItem(
        name: String,
        blockCreator: Function<BlockBehaviour.Properties, Block>,
        properties: BlockBehaviour.Properties,
    ): Block {
        val id = id(name)
        return BuiltInRegistries.BLOCK.register(id, blockCreator.apply(properties.setId(Registries.BLOCK.key(id))))
    }
    // endregion

}