package org.teamvoided.plushed.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponentGetter
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity.WobbleStyle
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import org.teamvoided.plushed.plushie.Plushie
import org.teamvoided.plushed.data.registry.PlushPlushies
import org.teamvoided.plushed.init.PlushBlockEntityTypes
import org.teamvoided.plushed.init.PlushDataComponents
import org.teamvoided.plushed.init.PlushRegistries
import kotlin.jvm.optionals.getOrNull

class PlushieBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(PlushBlockEntityTypes.PLUSHIE, pos, state) {

    private var customName: Component? = null
    private var plushie: Holder<Plushie>? = null
    var squishStartedAtTick: Long = 0

    fun squish() {
        val level = level
        if (level != null && !level.isClientSide) {
            level.blockEvent(blockPos, blockState.block, 1, 0)
        }
    }

    override fun triggerEvent(i: Int, j: Int): Boolean {
        if (level != null && i == 1 && j >= 0 && j < WobbleStyle.entries.toTypedArray().size) {
            squishStartedAtTick = level!!.gameTime
            return true
        } else {
            return super.triggerEvent(i, j)
        }
    }


    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket = ClientboundBlockEntityDataPacket.create(this)

    override fun getUpdateTag(provider: HolderLookup.Provider): CompoundTag {
        return saveCustomOnly(provider)
    }

    override fun saveAdditional(output: ValueOutput) {
        super.saveAdditional(output)
        output.storeNullable(TAG_CUSTOM_NAME, ComponentSerialization.CODEC, customName)
        plushie?.let { plushie ->
            output.store(TAG_PLUSHIE, Plushie.CODEC, plushie)
        }
    }

    override fun loadAdditional(input: ValueInput) {
        super.loadAdditional(input)
        customName = parseCustomNameSafe(input, TAG_CUSTOM_NAME)
        plushie = input.read(TAG_PLUSHIE, Plushie.CODEC).getOrNull()
    }


    override fun applyImplicitComponents(getter: DataComponentGetter) {
        super.applyImplicitComponents(getter)
        customName = getter.get(DataComponents.CUSTOM_NAME)
        plushie = getter.get(PlushDataComponents.PLUSHIE)
    }

    override fun collectImplicitComponents(builder: DataComponentMap.Builder) {
        super.collectImplicitComponents(builder)
        builder.set(DataComponents.CUSTOM_NAME, customName)
        plushie?.let { plushie ->
            builder.set(PlushDataComponents.PLUSHIE, plushie)
        }
    }

    fun getPlushie(): Holder<Plushie> = plushie ?: level!!.holderLookup(PlushRegistries.PLUSHIE)[PlushPlushies.FALLBACK].get()

    companion object {
        private const val TAG_CUSTOM_NAME = "custom_name"
        private const val TAG_PLUSHIE = "plushie"

    }
}
