package org.teamvoided.plushed.init

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.block.entity.PlushieBlockEntity
import org.teamvoided.plushed.util.register

object PlushBlockEntityTypes {

    val PLUSHIE = register(
        "plushie", FabricBlockEntityTypeBuilder.Factory(::PlushieBlockEntity),
        PlushBlocks.PLUSHIE_BLOCK
    )

    fun init() = Unit

    fun <T : BlockEntity> register(
        string: String, blockEntitySupplier: FabricBlockEntityTypeBuilder.Factory<T>, vararg blocks: Block,
    ): BlockEntityType<T> {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.register(
            id(string), FabricBlockEntityTypeBuilder.create(blockEntitySupplier, *blocks).build()
        )
    }
}