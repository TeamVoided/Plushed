package org.teamvoided.plushed.init

import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.plushie.Plushie
import org.teamvoided.plushed.util.register

object PlushDataComponents {

    val PLUSHIE = register("plushie") { builder ->
        builder.persistent(Plushie.CODEC).networkSynchronized(Plushie.STREAM_CODEC)
    }

    fun init() = Unit

    fun <T : Any> register(
        name: String, data: (DataComponentType.Builder<T>) -> DataComponentType.Builder<T>,
    ): DataComponentType<T> = register(name, data.invoke(DataComponentType.Builder()))

    fun <T : Any> register(name: String, data: DataComponentType.Builder<T>): DataComponentType<T> {
        return BuiltInRegistries.DATA_COMPONENT_TYPE.register(id(name), data.build())
    }

}