package com.koteldlc.client.modules.visual

import com.koteldlc.client.modules.Category
import com.koteldlc.client.modules.Module
import com.mojang.blaze3d.systems.RenderSystem

abstract class BaseEspModule(name: String) : Module(name, Category.VISUAL) {
    init {
        settings["color"] = 0xFFFF4444.toInt()
        settings["thickness"] = 2.0
        settings["blink"] = false
    }

    fun renderEsp() {
        if (!enabled) return
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.lineWidth((settings["thickness"] as Double).toFloat())

        val argb = settings["color"] as Int
        val a = ((argb shr 24) and 0xFF) / 255.0f
        val r = ((argb shr 16) and 0xFF) / 255.0f
        val g = ((argb shr 8) and 0xFF) / 255.0f
        val b = (argb and 0xFF) / 255.0f
        RenderSystem.setShaderColor(r, g, b, a)

        if (settings["blink"] as Boolean) {
            // Здесь можно добавить пульсацию альфа-канала/яркости.
        }

        onRenderEsp()
        RenderSystem.disableBlend()
    }

    protected abstract fun onRenderEsp()
}
