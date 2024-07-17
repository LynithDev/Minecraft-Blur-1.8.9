package dev.lynith.blurmod.blur

import dev.lynith.blurmod.blur.impl.GaussianBlur
import dev.lynith.blurmod.blur.impl.KawaseBlur

object BlurManager {

    private val methods = mutableListOf<BlurMethod>()
    var current: BlurMethod
        private set

    fun register(method: BlurMethod) {
        methods.add(method)
    }

    init {
        register(GaussianBlur())
        register(KawaseBlur())

        current = methods.first()
    }

}