package com.koteldlc.client.modules.visual

class TargetESP : BaseEspModule("TargetESP") {
    init {
        settings["color"] = 0xFFFF00FF.toInt()
        settings["thickness"] = 3.5
        settings["blink"] = true
    }

    override fun onRenderEsp() {
        // Подсветка текущей цели KillAura.
    }
}
