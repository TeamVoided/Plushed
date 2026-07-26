package org.teamvoided.plushed.init

import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.minecraft.resources.ResourceKey
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.plushie.Plushie

object PlushRegistries {

    val PLUSHIE = key<Plushie>("plushie")

    fun init() {
        DynamicRegistries.registerSynced(PLUSHIE, Plushie.DIRECT_CODEC)
    }

    fun <T : Any> key(id: String) = ResourceKey.createRegistryKey<T>(id(id))

}