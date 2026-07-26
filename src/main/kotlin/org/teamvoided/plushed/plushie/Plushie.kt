package org.teamvoided.plushed.plushie

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.Identifier
import net.minecraft.resources.RegistryFixedCodec
import net.minecraft.sounds.SoundEvent
import org.teamvoided.plushed.init.PlushRegistries
import java.util.*
import kotlin.jvm.optionals.getOrElse

data class Plushie(
    val visuals: PlushieVisuals,
    val useSound: Holder<SoundEvent>,
    val hiddenInTab: Optional<Boolean>,
) {

    fun isHiddenInTab() = hiddenInTab.getOrElse { false }

    constructor(modelId: Identifier, name: Component, useSound: Holder<SoundEvent>)
            : this(PlushieVisuals(modelId, name), useSound, Optional.empty())

    companion object {

        val DIRECT_CODEC: Codec<Plushie> = RecordCodecBuilder.create { inst ->
            inst
                .group(
                    PlushieVisuals.DIRECT_CODEC.fieldOf("visuals").forGetter(Plushie::visuals),
                    SoundEvent.CODEC.fieldOf("use_sound").forGetter(Plushie::useSound),
                    Codec.BOOL.optionalFieldOf("hidden_in_tab").forGetter(Plushie::hiddenInTab)
                )
                .apply(inst, ::Plushie)
        }
        val DIRECT_STREAM_CODEC = StreamCodec.composite(
            PlushieVisuals.STREAM_CODEC, Plushie::visuals,
            SoundEvent.STREAM_CODEC, Plushie::useSound,
            ByteBufCodecs.optional(ByteBufCodecs.BOOL), Plushie::hiddenInTab,
            ::Plushie
        )

        val CODEC = RegistryFixedCodec.create(PlushRegistries.PLUSHIE)
        val STREAM_CODEC = ByteBufCodecs.holder(PlushRegistries.PLUSHIE, DIRECT_STREAM_CODEC)

    }
}
