package dev.lynith.blurmod.blur.impl

import dev.lynith.blurmod.blur.BlurMethod
import dev.lynith.blurmod.shader.Shader
import dev.lynith.blurmod.utils.Fbo
import dev.lynith.blurmod.utils.Render
import dev.lynith.blurmod.utils.Stencil
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.shader.Framebuffer
import org.lwjgl.BufferUtils
import kotlin.math.exp
import kotlin.math.sqrt


class GaussianBlur(
    val radius: Float = 40f,
    val compression: Float = 2f
) : BlurMethod(
    name = "Gaussian",
    shader = arrayOf(
        Shader.createShaderFromPath("/assets/blurmod/shaders/gaussian.fsh", Shader.FRAGMENT),
        Shader.DEFAULT_VERTEX_SHADER
    )
) {

    private inline val mc get() = Minecraft.getMinecraft()
    private var framebuffer = Framebuffer(1, 1, false)

    private fun setupUniforms(dir1: Float, dir2: Float) {
        program["textureIn"] = 0
        program["textureSize"] = floatArrayOf(1f / mc.displayWidth, 1f / mc.displayHeight)
        program["direction"] = floatArrayOf(dir1, dir2)
        program["radius"] = radius

        val weightBuffer = BufferUtils.createFloatBuffer(256)
        for (i in 0..radius.toInt()) {
            weightBuffer.put(calculateGaussianValue(i.toFloat(), radius / 2))
        }

        weightBuffer.rewind()
        program["weights"] = weightBuffer
    }

    fun start() {
        Stencil.use()
    }

    fun end() {
        Stencil.read()

        framebuffer = Fbo.createFrameBuffer(framebuffer)

        framebuffer.framebufferClear()
        framebuffer.bindFramebuffer(false)
        program.begin()
        setupUniforms(compression, 0f)

        GlStateManager.bindTexture(mc.framebuffer.framebufferTexture)
        Render.drawQuads()
        framebuffer.unbindFramebuffer()
        program.end()

        mc.framebuffer.bindFramebuffer(false)
        program.begin()
        setupUniforms(0f, compression)

        GlStateManager.bindTexture(framebuffer.framebufferTexture)
        Render.drawQuads()
        program.end()

        Stencil.end()
        GlStateManager.color(1f, 1f, 1f, 1f)
        GlStateManager.bindTexture(0)
    }

    override fun drawBlurred(block: () -> Unit) {
        start()
        block()
        end()
    }

    private fun calculateGaussianValue(x: Float, sigma: Float): Float {
        val output = 1.0 / sqrt(2.0 * Math.PI * (sigma * sigma))
        return (output * exp(-(x * x) / (2.0 * (sigma * sigma)))).toFloat()
    }

}