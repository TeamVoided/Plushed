package org.teamvoided.plushed.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup.RegistryLookup
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import org.teamvoided.plushed.Plushed.MODID
import org.teamvoided.plushed.Plushed.id
import org.teamvoided.plushed.plushie.Plushie
import org.teamvoided.plushed.util.getModEntries
import org.teamvoided.plushed.util.registerHolder

object PlushTabs {

    val TABS get() = getModEntries(BuiltInRegistries.CREATIVE_MODE_TAB)

    val PLUSHED = register(
        "plushed", FabricItemGroup.builder()
            .icon { Items.WHITE_WOOL.defaultInstance }
            .title(Component.translatable("itemGroup.$MODID.plushed"))
            .displayItems { params, output ->
                params.holders.lookup(PlushRegistries.PLUSHIE).ifPresent {
                    addPlushies(output, it)
                }
            }
    )

//    val PLUSHIE_COMPARATOR: Comparator<Holder<Plushie>> = comparing(
//        Holder<Plushie>::value, comparingInt(Plushie::area).thenComparing(Plushie::width)
//    )

    fun addPlushies(output: CreativeModeTab.Output, lookup: RegistryLookup<Plushie>) {
        lookup.listElements()
            .filter { !it.value().isHiddenInTab() }
//            .sorted(PLUSHIE_COMPARATOR)
            .forEach { holder ->
                val stack = ItemStack(PlushItems.PLUSHIE)
                stack.set(PlushDataComponents.PLUSHIE, holder)
                stack.set(DataComponents.ITEM_MODEL, holder.value().visuals.modelId)
                output.accept(stack)
            }
    }

    fun init() = Unit

    fun register(name: String, tabBuilder: CreativeModeTab.Builder): Holder.Reference<CreativeModeTab> {
        return BuiltInRegistries.CREATIVE_MODE_TAB.registerHolder(id(name), tabBuilder.build())
    }

}