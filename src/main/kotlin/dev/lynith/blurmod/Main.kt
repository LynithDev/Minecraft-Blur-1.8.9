package dev.lynith.dev.lynith.blurmod

import dev.lynith.blurmod.blur.BlurManager
import net.minecraft.client.gui.Gui
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent

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
        BlurManager.current.drawBlurred {
            Gui.drawRect(25, 25, 150, 150, 0xFFFFFFFF.toInt())
        }
    }

}