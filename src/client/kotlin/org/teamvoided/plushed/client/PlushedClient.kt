package org.teamvoided.plushed.client

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import org.teamvoided.plushed.Plushed
import org.teamvoided.plushed.client.renderer.blockentity.PlushieBlockEntityRenderer
import org.teamvoided.plushed.init.PlushBlockEntityTypes

object PlushedClient {

    fun init() {
        Plushed.log.info("Hello from Client")
//        BlockRenderLayerMap.putBlock(PlushBlocks.PLUSHIE_BLOCK, ChunkSectionLayer.TRANSLUCENT)

        BlockEntityRenderers
            .register(PlushBlockEntityTypes.PLUSHIE, BlockEntityRendererProvider(::PlushieBlockEntityRenderer))

    }

}