package org.teamvoided.plushed.datagen.assets

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import org.teamvoided.plushed.util.getModHolders
import java.util.concurrent.CompletableFuture

class EnLangProvider(var output: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(output, p) {

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        getModHolders(BuiltInRegistries.ITEM)
            .forEach { gen.add(it.value(), genLang(it.key().identifier())) }
        getModHolders(BuiltInRegistries.BLOCK)
            .forEach { trySafe { gen.add(it.value(), genLang(it.key().identifier())) } }
        getModHolders(BuiltInRegistries.CREATIVE_MODE_TAB)
            .forEach { gen.add(it.key(), genLang(it.key().identifier())) }

    }

    private fun genLang(id: Identifier): String =
        id.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }


    fun trySafe(fn: () -> Unit) {
        try {
            fn()
        } catch (e: Exception) {
            if (output.isStrictValidationEnabled) {
                LOGGER.warn("Exception found when lang gen: ", e)
            }
        }
    }

}