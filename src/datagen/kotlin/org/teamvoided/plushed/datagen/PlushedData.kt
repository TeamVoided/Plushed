package org.teamvoided.plushed.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderLookup.RegistryLookup
import net.minecraft.core.RegistrySetBuilder
import org.teamvoided.plushed.Plushed
import org.teamvoided.plushed.Plushed.log
import org.teamvoided.plushed.datagen.assets.EnLangProvider
import org.teamvoided.plushed.datagen.assets.ModelProvider
import org.teamvoided.plushed.datagen.data.BlockLootTableProvider
import org.teamvoided.plushed.datagen.data.registry.Plushies
import org.teamvoided.plushed.datagen.data.tags.BlockTagsProvider
import org.teamvoided.plushed.datagen.data.tags.ItemTagsProvider
import org.teamvoided.plushed.init.PlushRegistries
import java.util.concurrent.CompletableFuture

object PlushedData : DataGeneratorEntrypoint {

    override fun getEffectiveModId(): String = Plushed.MODID

    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()
        log.info("Running \"${gen.modContainer.metadata.name}\" Datagen!")

        // Assets
        pack.addProvider(::EnLangProvider)
        pack.addProvider(::ModelProvider)
        // Data
        pack.addProvider(::RegistryProvider)
        val blockTags = pack.addProvider(::BlockTagsProvider)
        pack.addProvider { o, f -> ItemTagsProvider(o, f, blockTags) }
        pack.addProvider(::BlockLootTableProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(PlushRegistries.PLUSHIE, Plushies::bootstrap)
    }

    class RegistryProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) :
        FabricDynamicRegistryProvider(o, p) {

        override fun getName(): String = "Registry Gen"

        override fun configure(provider: HolderLookup.Provider, entries: Entries) {
            entries.addEverything(provider.lookupOrThrow(PlushRegistries.PLUSHIE))
        }

        fun <T : Any> Entries.addEverything(registry: RegistryLookup<T>): List<Holder<T>> {
            return registry.listElementIds().map { add(registry, it) }.toList()
        }

    }
}