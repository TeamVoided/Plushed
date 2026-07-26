package org.teamvoided.plushed.client.renderer.blockentity.state

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import org.teamvoided.plushed.Plushed.mc
import org.teamvoided.plushed.plushie.PlushieVisuals

class PlushieRenderState : BlockEntityRenderState() {
    var facing = Direction.NORTH
    var plushieVisuals: PlushieVisuals = PlushieVisuals(mc("diamond"), Component.literal("fake and made up"))
    var plushieModel = ItemStackRenderState()
    var animationProgress = 0f
}
