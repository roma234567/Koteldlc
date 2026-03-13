package com.koteldlc.client.modules.visual

class HurtTimeESP : BaseEspModule("HurtTimeESP") {
    init {
        settings["color"] = 0xFFFF2D2D.toInt()
        settings["durationSec"] = 1.2
        settings["blink"] = true
    }

    override fun onRenderEsp() {
        // Подсветка игроков, получивших урон в последние durationSec секунд.
    }
}
