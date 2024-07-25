package dev.lynith.blurmod.utils

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*


object Render {

    private inline val mc get() = Minecraft.getMinecraft()

    fun drawQuads(
        left: Float = 0f,
        top: Float = 0f,
        right: Float = ScaledResolution(mc).scaledWidth_double.toFloat(),
        bottom: Float = ScaledResolution(mc).scaledHeight_double.toFloat(),
    ) {
        glBegin(GL_QUADS)
        glTexCoord2f(0f, 1f)
        glVertex2f(left, top)
        glTexCoord2f(0f, 0f)
        glVertex2f(left, bottom)
        glTexCoord2f(1f, 0f)
        glVertex2f(right, bottom)
        glTexCoord2f(1f, 1f)
        glVertex2f(right, top)
        glEnd()

//        glBegin(GL_QUADS)
//        glTexCoord2f(0f, 1f)
//        glVertex2f(0f, 0f)
//        glTexCoord2f(0f, 0f)
//        glVertex2f(0f, height)
//        glTexCoord2f(1f, 0f)
//        glVertex2f(width, height)
//        glTexCoord2f(1f, 1f)
//        glVertex2f(width, 0f)
//        glEnd()
    }

    // copied from Gui.drawRect lol
    fun drawTriangle(left: Int, top: Int, bottom: Int, right: Int, color: Int) {
        var i: Int
        var left = left
        var right = right
        var top = top
        var bottom = bottom

        if (left < right) {
            i = left
            left = right
            right = i
        }

        if (top < bottom) {
            i = top
            top = bottom
            bottom = i
        }

        val f = (color shr 24 and 255).toFloat() / 255.0f
        val g = (color shr 16 and 255).toFloat() / 255.0f
        val h = (color shr 8 and 255).toFloat() / 255.0f
        val j = (color and 255).toFloat() / 255.0f
        val tessellator = Tessellator.getInstance()
        val worldRenderer = tessellator.worldRenderer
        GlStateManager.enableBlend()
        GlStateManager.disableTexture2D()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.color(g, h, j, f)
        worldRenderer.begin(GL_TRIANGLES, DefaultVertexFormats.POSITION)
        worldRenderer.pos(left.toDouble(), bottom.toDouble(), 0.0).endVertex()
        worldRenderer.pos(right.toDouble(), bottom.toDouble(), 0.0).endVertex()
        worldRenderer.pos((left + right shr 1).toDouble(), top.toDouble(), 0.0).endVertex()
        tessellator.draw()
        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()
    }

}