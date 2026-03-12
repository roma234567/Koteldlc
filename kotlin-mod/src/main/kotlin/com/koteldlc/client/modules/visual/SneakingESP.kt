package com.koteldlc.client.modules.visual

class SneakingESP : BaseEspModule("SneakingESP") {
    init {
        settings["color"] = 0xFF56A8FF.toInt()
    }

    override fun onRenderEsp() {
        // Подсветка игроков в состоянии sneak.
    }
}
