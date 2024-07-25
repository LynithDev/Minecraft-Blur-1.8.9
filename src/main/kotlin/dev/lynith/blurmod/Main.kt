package dev.lynith.dev.lynith.blurmod

import dev.lynith.blurmod.blur.BlurManager
import dev.lynith.blurmod.utils.Render
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent
import org.lwjgl.opengl.GL11

@Mod(
    name = "Blur Mod",
    modid = "blurmod",
    version = "1.0.0",
)
class Main {

    @Mod.EventHandler
    fun onInitialize(event: FMLInitializationEvent) {
        println("Blur Mod initialized")
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onRenderTick(event: RenderTickEvent) {
        Gui.drawRect(75, 75, 100, 100, 0xFFFF0000.toInt())
//        BlurManager.current.drawBlurred {
//            Render.drawTriangle(300, 50, 100, 100, 0xFF00FF00.toInt())
//        }

        BlurManager.current.drawBlurred {
//            Gui.drawRect(25, 25, 150, 150, 0xFFFFFFFF.toInt())

            GL11.glPushMatrix()
            GL11.glRotatef(45f, 0f, 0f, 1f)
            Render.drawTriangle(300, 50, 100, 100, 0xFF00FF00.toInt())
            GL11.glPopMatrix()

            val sr = ScaledResolution(Minecraft.getMinecraft())
            val left = 50f / sr.scaleFactor
            val top = 50f / sr.scaleFactor
            val right = 100f / sr.scaleFactor
            val bottom = 100f / sr.scaleFactor

            GlStateManager.disableTexture2D()
            GlStateManager.color(0f, 0f, 0f, 1f)
            Render.drawQuads(left, top, right, bottom)
            GlStateManager.enableTexture2D()

//            val sr = ScaledResolution(Minecraft.getMinecraft())
//            val x = sr.scaledWidth - 100
//            val y = sr.scaledHeight - 100
//            Gui.drawRect(x, y, x + 50, y + 50, 0xFFFFFFFF.toInt())
        }
    }

}