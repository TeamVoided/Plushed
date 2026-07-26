package org.teamvoided.plushed.client.renderer.blockentity

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator
import com.mojang.math.Axis
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.block.ModelBlockRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay
import net.minecraft.client.renderer.state.CameraRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.client.resources.model.ModelBakery
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.util.Mth
import net.minecraft.world.item.Items
import net.minecraft.world.phys.Vec3
import org.teamvoided.plushed.block.PlushieBlock
import org.teamvoided.plushed.block.entity.PlushieBlockEntity
import org.teamvoided.plushed.client.renderer.blockentity.state.PlushieRenderState
import kotlin.jvm.optionals.getOrNull

class PlushieBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    BlockEntityRenderer<PlushieBlockEntity, PlushieRenderState> {
    private val blockDispatcher = context.blockRenderDispatcher()
    private val itemModelResolver = context.itemModelResolver()

    var durationInTicks: Float = 5f

    override fun createRenderState(): PlushieRenderState = PlushieRenderState()

    override fun extractRenderState(
        plush: PlushieBlockEntity, state: PlushieRenderState,
        tickDelta: Float, vec3: Vec3, crumblingOverlay: CrumblingOverlay?,
    ) {
        super.extractRenderState(plush, state, tickDelta, vec3, crumblingOverlay)
        state.facing = plush.blockState.getValue(PlushieBlock.FACING)
        state.plushieVisuals = plush.getPlushie().value().visuals

        val stack = Items.DIAMOND_BLOCK.defaultInstance
        stack[DataComponents.ITEM_MODEL] = state.plushieVisuals.modelId
        itemModelResolver.updateForTopItem(
            state.plushieModel,
            stack,
            state.plushieVisuals.displayContext,
            plush.level,
            null,
            plush.blockPos.asLong().toInt()
        )

        if (plush.squishStartedAtTick != 0L && plush.getLevel() != null) {
            state.animationProgress =
                ((plush.getLevel()!!.gameTime - plush.squishStartedAtTick) + tickDelta) / durationInTicks
        } else {
            state.animationProgress = 0.0f
        }
    }

    override fun submit(
        state: PlushieRenderState, poseStack: PoseStack, collector: SubmitNodeCollector, camera: CameraRenderState,
    ) {
        poseStack.pushPose()

        if (state.animationProgress in 0f..1f) {
            val f = 0.015625f
            val g = (state.animationProgress * Math.PI).toFloat()
            val h = -10 * (Mth.cos(g.toDouble()) + 0.5f) * Mth.sin(g.toDouble())
            val scale = h * f
            val sideScale = scale * -2f
            poseStack.translate(sideScale / -2f, 0f, sideScale / -2f)
            poseStack.scale(1f + sideScale, 1f + scale, 1f + sideScale)
        }

        val offset = 0.5f
        poseStack.translate(offset, offset, offset)
        poseStack.mulPose(
            Axis.YP.rotationDegrees(-Direction.from2DDataValue((90 + state.facing.get2DDataValue()) % 4).toYRot())
        )
        val vec = state.plushieVisuals.translation.getOrNull()
        if (vec != null) poseStack.translate(vec.x, vec.y, vec.z)
        state.plushieModel.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0)
        poseStack.translate(-offset, -offset, -offset)
        if (vec != null) poseStack.translate(-vec.x, -vec.y, -vec.z)

        val model = blockDispatcher.getBlockModel(state.blockState)
        val breakProgress = state.breakProgress
        if (breakProgress != null) {
            collector.submitCustomGeometry(
                poseStack, ModelBakery.DESTROY_TYPES[breakProgress.progress()]
            ) { pose, consumer ->
                val decal = SheetedDecalTextureGenerator(consumer, breakProgress.cameraPose(), 1f)
                ModelBlockRenderer.renderModel(
                    pose, decal, model,
                    0.0f, 0.0f, 0.0f,
                    state.lightCoords, OverlayTexture.NO_OVERLAY
                )
            }
        }

        poseStack.popPose()
    }

}