package org.teamvoided.plushed.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.plushed.block.entity.PlushieBlockEntity
import org.teamvoided.plushed.init.PlushStats

class PlushieBlock(properties: Properties) : BaseEntityBlock(properties) {

    init {
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH))
    }

    override fun codec(): MapCodec<PlushieBlock> = CODEC

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = PlushieBlockEntity(pos, state)

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        return defaultBlockState()
            .setValue(FACING, ctx.horizontalDirection.opposite)
    }

    override fun useWithoutItem(
        blockState: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        blockHitResult: BlockHitResult,
    ): InteractionResult {
        if (!level.isClientSide) {
            val plush = level.getBlockEntity(pos)
            if (plush is PlushieBlockEntity) {
                plush.squish()
                player.awardStat(PlushStats.INTERACT_WITH_PLUSHIES)
                level.playSound(null, pos, plush.getPlushie().value().useSound.value(), SoundSource.BLOCKS, 1f, 1f)
            }
        }

        return InteractionResult.SUCCESS
    }

    override fun getRenderShape(blockState: BlockState): RenderShape = RenderShape.INVISIBLE

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPE
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    companion object {

        val CODEC = simpleCodec(::PlushieBlock)
        val FACING = HorizontalDirectionalBlock.FACING
        val SHAPE = column(12.0, 0.0, 16.0)

    }
}
