package dev.lynith.blurmod.utils

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.shader.Framebuffer
import org.lwjgl.opengl.GL11.*


object Render {

    private inline val mc get() = Minecraft.getMinecraft()

    fun drawQuads() {
        val sr = ScaledResolution(mc)
        val width = sr.scaledWidth_double.toFloat()
        val height = sr.scaledHeight_double.toFloat()

        glBegin(GL_QUADS)
        glTexCoord2f(0f, 1f)
        glVertex2f(0f, 0f)
        glTexCoord2f(0f, 0f)
        glVertex2f(0f, height)
        glTexCoord2f(1f, 0f)
        glVertex2f(width, height)
        glTexCoord2f(1f, 1f)
        glVertex2f(width, 0f)
        glEnd()
    }

}