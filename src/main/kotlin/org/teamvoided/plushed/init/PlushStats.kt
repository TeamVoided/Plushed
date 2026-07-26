package org.teamvoided.plushed.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.stats.StatFormatter
import net.minecraft.stats.Stats
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.util.register

object PlushStats {

    val INTERACT_WITH_PLUSHIES = register("interact_with_plushies")
    val PLUSHIES_THROWN = register("plushies_thrown")

    fun init() = Unit

    private fun register(name: String, formatter: StatFormatter = StatFormatter.DEFAULT): Identifier {
        val id = id(name)
        BuiltInRegistries.CUSTOM_STAT.register(id, id)
        Stats.CUSTOM.get(id, formatter)
        return id
    }

}