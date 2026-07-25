package org.teamvoided.plushed.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.Item
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.util.getModEntries
import org.teamvoided.plushed.util.register

object PlushedItems {

    val ITEMS get() = getModEntries(BuiltInRegistries.ITEM)

//    val APPLE_2 = register("apple_2", Item(Properties().rarity(Rarity.RARE)))

    fun init() = Unit

    fun register(id: String, item: Item): Item = BuiltInRegistries.ITEM.register(id(id), item)

}