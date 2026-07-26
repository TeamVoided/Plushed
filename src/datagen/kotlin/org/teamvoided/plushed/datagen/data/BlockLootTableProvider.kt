package org.teamvoided.plushed.datagen.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponents
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import org.teamvoided.plushed.init.PlushBlocks
import org.teamvoided.plushed.init.PlushDataComponents
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, l: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, l) {

    override fun generate() {
        add(PlushBlocks.PLUSHIE_BLOCK) { block ->
            LootTable.lootTable().withPool(
                applyExplosionCondition(
                    block, LootPool.lootPool()
                        .add(
                            LootItem.lootTableItem(block).apply(
                                CopyComponentsFunction
                                    .copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                                    .include(DataComponents.CUSTOM_NAME)
                                    .include(DataComponents.ITEM_MODEL)
                                    .include(PlushDataComponents.PLUSHIE)
                            )
                        )
                )
            )
        }
    }


}