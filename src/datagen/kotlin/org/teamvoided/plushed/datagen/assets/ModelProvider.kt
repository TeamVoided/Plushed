package org.teamvoided.plushed.datagen.assets


import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    override fun generateBlockStateModels(gen: BlockModelGenerators) {
//        gen.createTrivialCube(TemplateBlocks.APPLE_BLOCK)
//        gen.createTrivialBlock(TemplateBlocks.SPECIAL_APPLE_BLOCK, TexturedModel.LEAVES)
    }

//    val single = listOf(TemplateItems.APPLE_2)

    override fun generateItemModels(gen: ItemModelGenerators) {
//        single.forEach { gen.generateFlatItem(it, ModelTemplates.FLAT_ITEM) }
    }

}