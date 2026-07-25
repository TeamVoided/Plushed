package org.teamvoided.plushed.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.util.tag

object PlushedBlockTags {

    val APPLE_LIKE = create("apple_like")

    fun create(id: String) = Registries.BLOCK.tag(id(id))

}