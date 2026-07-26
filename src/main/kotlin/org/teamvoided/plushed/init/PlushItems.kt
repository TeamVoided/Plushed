package org.teamvoided.plushed.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.item.PlushieBlockItem
import org.teamvoided.plushed.util.getModEntries
import org.teamvoided.plushed.util.key
import org.teamvoided.plushed.util.register
import java.util.function.Function

object PlushItems {

    val ITEMS get() = getModEntries(BuiltInRegistries.ITEM)

    val PLUSHIE = register("plushie", { PlushieBlockItem(PlushBlocks.PLUSHIE_BLOCK, it) }, Properties())

    fun init() = Unit

    fun register(name: String, function: Function<Properties, Item>, properties: Properties): Item {
        val id = id(name)
        return BuiltInRegistries.ITEM.register(id, function.apply(properties.setId(Registries.ITEM.key(id))))
    }

}