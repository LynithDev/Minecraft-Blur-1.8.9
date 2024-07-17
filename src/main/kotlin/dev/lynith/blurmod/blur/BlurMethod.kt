package dev.lynith.blurmod.blur

import dev.lynith.blurmod.shader.Shader
import dev.lynith.blurmod.shader.ShaderProgram

abstract class BlurMethod(
    val name: String,
    vararg shader: Shader
) {

    val program = ShaderProgram(*shader)

    abstract fun drawBlurred(block: () -> Unit)

}