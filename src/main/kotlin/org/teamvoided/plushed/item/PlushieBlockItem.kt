package org.teamvoided.plushed.item

import net.minecraft.network.chat.Component
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block
import org.teamvoided.plushed.init.PlushDataComponents.PLUSHIE

class PlushieBlockItem(block: Block, properties: Properties) : BlockItem(block, properties) {

    override fun getName(stack: ItemStack): Component = stack.get(PLUSHIE)?.value()?.visuals?.name ?: super.getName(stack)

}