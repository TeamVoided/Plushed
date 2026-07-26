package org.teamvoided.plushed

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.resources.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.plushed.config.PlushedConfig
import org.teamvoided.plushed.init.PlushBlockEntityTypes
import org.teamvoided.plushed.init.PlushBlocks
import org.teamvoided.plushed.init.PlushDataComponents
import org.teamvoided.plushed.init.PlushItems
import org.teamvoided.plushed.init.PlushRegistries
import org.teamvoided.plushed.init.PlushStats
import org.teamvoided.plushed.init.PlushTabs

object Plushed {

    const val MODID = "plushed"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Plushed::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::PlushedConfig)

    fun init() {
        log.info("I’m going to turn all of you in to marketable plushies!")
        PlushRegistries.init()
        PlushStats.init()
        PlushBlocks.init()
        PlushDataComponents.init()
        PlushItems.init()
        PlushBlockEntityTypes.init()
        PlushTabs.init()
    }

    fun id(namespace: String, path: String): Identifier = Identifier.fromNamespaceAndPath(namespace, path)
    fun mc(path: String): Identifier = Identifier.withDefaultNamespace(path)
    fun id(path: String) = id(MODID, path)

}