package org.teamvoided.plushed.datagen.data.registry

import net.minecraft.core.Holder
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Component.translatable
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.phys.Vec3
import org.teamvoided.plushed.Plushed.mc
import org.teamvoided.plushed.data.registry.PlushPlushies
import org.teamvoided.plushed.plushie.Plushie
import org.teamvoided.plushed.plushie.PlushieVisuals
import java.util.*

object Plushies : RegistryBootstrapper<Plushie> {

    override fun BootstrapContext<Plushie>.init() {
        register(
            PlushPlushies.FALLBACK,
            Plushie(mc("diamond_block"), Component.literal("diamond block plushie"), SoundEvents.ARMOR_EQUIP_NAUTILUS)
        )
        plushie(
            PlushPlushies.TEST_1,
            PlushieVisuals(mc("apple"), translatable("gay")),
            SoundEvents.AMBIENT_BASALT_DELTAS_MOOD
        )
        plushie(
            PlushPlushies.TEST_2,
            PlushieVisuals(
                mc("trident"), translatable("Fork One Big!"),
                ItemDisplayContext.NONE, Optional.of(Vec3(0.5, 1.0, 0.5))
            ),
            SoundEvents.AMBIENT_CAVE
        )

        plushie(
            PlushPlushies.TEST_3,
            PlushieVisuals(
                mc("spyglass"), translatable("Eye spy my eye!"),
                ItemDisplayContext.NONE, Optional.empty()
            ),
            SoundEvents.GENERIC_DRINK
        )
    }

    fun BootstrapContext<Plushie>.plushie(
        key: ResourceKey<Plushie>,
        visuals: PlushieVisuals,
        useSound: Holder<SoundEvent>,
        hiddenInTab: Boolean = false,
    ) {
        register(key, Plushie(visuals, useSound, if (hiddenInTab) Optional.of(true) else Optional.empty()))
    }

}