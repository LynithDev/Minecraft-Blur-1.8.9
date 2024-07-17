package dev.lynith.blurmod.utils

import net.minecraft.client.Minecraft
import net.minecraft.client.shader.Framebuffer

object Fbo {

    private inline val mc get() = Minecraft.getMinecraft()

    fun createFrameBuffer(framebuffer: Framebuffer?, depth: Boolean = false): Framebuffer {
        if (needsNewFramebuffer(framebuffer)) {
            framebuffer?.deleteFramebuffer()
            return Framebuffer(mc.displayWidth, mc.displayHeight, depth)
        }
        return framebuffer!!
    }

    private fun needsNewFramebuffer(framebuffer: Framebuffer?): Boolean {
        return framebuffer == null || framebuffer.framebufferWidth != mc.displayWidth || framebuffer.framebufferHeight != mc.displayHeight
    }

}