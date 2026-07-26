package org.teamvoided.plushed.data.registry

import org.teamvoided.plushed.Plushed
import org.teamvoided.plushed.init.PlushRegistries
import org.teamvoided.plushed.util.key

object PlushPlushies {

    val FALLBACK = create("fallback")
    val TEST_1 = create("test_1")
    val TEST_2 = create("test_2")
    val TEST_3 = create("test_3")

    fun create(id: String) = PlushRegistries.PLUSHIE.key(Plushed.id(id))

}