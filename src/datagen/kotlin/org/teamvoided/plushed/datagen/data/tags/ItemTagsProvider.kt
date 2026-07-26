package org.teamvoided.plushed.datagen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.ItemTags
import org.teamvoided.plushed.data.tags.PlushBlockTags
import org.teamvoided.plushed.data.tags.PlushItemTags
import java.util.concurrent.CompletableFuture

class ItemTagsProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>, blockTag: BlockTagProvider) :
    ItemTagProvider(o, p, blockTag) {

    override fun addTags(lookup: HolderLookup.Provider) {
        copy(PlushBlockTags.APPLE_LIKE, PlushItemTags.APPLE_LIKE)
        copy(ConventionalBlockTags.STORAGE_BLOCKS, ConventionalItemTags.STORAGE_BLOCKS)

        valueLookupBuilder(ItemTags.SWORDS)
//            .add(TemplateBlocks.APPLE_BLOCK.asItem())
        valueLookupBuilder(PlushItemTags.APPLE_LIKE)
//            .add(TemplateItems.APPLE_2)
        valueLookupBuilder(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS)
//            .add(TemplateItems.APPLE_2)
    }

}