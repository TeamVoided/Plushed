package org.teamvoided.plushed.datagen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import org.teamvoided.plushed.data.tags.PlushBlockTags
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) : BlockTagProvider(o, p) {

    override fun addTags(lookup: HolderLookup.Provider) {
        valueLookupBuilder(BlockTags.LOGS)
//            .add(TemplateBlocks.SPECIAL_APPLE_BLOCK)

        valueLookupBuilder(PlushBlockTags.APPLE_LIKE)
//            .add(TemplateBlocks.APPLE_BLOCK, Blocks.RED_TERRACOTTA)

        valueLookupBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
            .forceAddTag(PlushBlockTags.APPLE_LIKE)
    }

}