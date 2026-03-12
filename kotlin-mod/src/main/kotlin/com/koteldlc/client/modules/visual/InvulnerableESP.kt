package com.koteldlc.client.modules.visual

class InvulnerableESP : BaseEspModule("InvulnerableESP") {
    init {
        settings["color"] = 0xFFFFD700.toInt()
    }

    override fun onRenderEsp() {
        // Подсветка игроков с флагом неуязвимости.
    }
}
