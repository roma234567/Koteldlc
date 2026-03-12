package com.koteldlc.client.modules.visual

class GlidingESP : BaseEspModule("GlidingESP") {
    init {
        settings["color"] = 0xFFA56BFF.toInt()
    }

    override fun onRenderEsp() {
        // Подсветка игроков, летящих на элитрах.
    }
}
