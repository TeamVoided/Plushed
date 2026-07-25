package org.teamvoided.plushed.data.registry

import net.minecraft.core.registries.Registries
import org.teamvoided.plushed.Plushed
import org.teamvoided.plushed.util.key

object PlushedPaintings {

    val APPLE_PAINT = create("apple_paint")

    fun create(id: String) = Registries.PAINTING_VARIANT.key(Plushed.id(id))

}