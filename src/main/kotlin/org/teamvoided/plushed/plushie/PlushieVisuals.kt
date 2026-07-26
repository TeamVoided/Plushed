package org.teamvoided.plushed.plushie

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.Identifier
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.phys.Vec3
import java.util.*

data class PlushieVisuals(
    val modelId: Identifier,
    val name: Component,
    val displayContext: ItemDisplayContext,
    val translation: Optional<Vec3>,
) {
    constructor(modelId: Identifier, name: Component) : this(modelId, name, ItemDisplayContext.NONE, Optional.empty())

    companion object {
        val DIRECT_CODEC: Codec<PlushieVisuals> = RecordCodecBuilder.create { inst ->
            inst
                .group(
                    Identifier.CODEC.fieldOf("model_id").forGetter(PlushieVisuals::modelId),
                    ComponentSerialization.CODEC.fieldOf("name").forGetter(PlushieVisuals::name),
                    ItemDisplayContext.CODEC.fieldOf("display_context").forGetter(PlushieVisuals::displayContext),
                    Vec3.CODEC.optionalFieldOf("translation").forGetter(PlushieVisuals::translation),
                )
                .apply(inst, ::PlushieVisuals)
        }
        val STREAM_CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC, PlushieVisuals::modelId,
            ComponentSerialization.TRUSTED_STREAM_CODEC, PlushieVisuals::name,
            ByteBufCodecs.BYTE.map({ ItemDisplayContext.BY_ID.apply(it.toInt()) }, { it.id }),
            PlushieVisuals::displayContext,
            ByteBufCodecs.optional(Vec3.STREAM_CODEC), PlushieVisuals::translation,
            ::PlushieVisuals
        )
    }
}