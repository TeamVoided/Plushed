package org.teamvoided.plushed

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.resources.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.plushed.config.PlushedConfig
import org.teamvoided.plushed.init.PlushedBlocks
import org.teamvoided.plushed.init.PlushedItems

object Plushed {

    const val MODID = "plushed"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Plushed::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::PlushedConfig)

    fun init() {
        log.info("Hello from Common ${config.commonEntry.get()}")
        PlushedItems.init()
        PlushedBlocks.init()
    }

    fun id(namespace: String, path: String): Identifier = Identifier.fromNamespaceAndPath(namespace, path)
    fun mc(path: String): Identifier = Identifier.withDefaultNamespace(path)
    fun id(path: String) = id(MODID, path)

}