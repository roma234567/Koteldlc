package com.koteldlc.client.modules.visual

class SleepingESP : BaseEspModule("SleepingESP") {
    init {
        settings["color"] = 0xFFFFFFFF.toInt()
    }

    override fun onRenderEsp() {
        // Подсветка спящих игроков.
    }
}
