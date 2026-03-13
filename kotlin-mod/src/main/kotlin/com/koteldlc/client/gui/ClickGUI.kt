package com.koteldlc.client.gui

import com.koteldlc.client.ModuleManager
import com.koteldlc.client.modules.Category
import com.koteldlc.client.modules.Module

data class ModuleButton(
    val module: Module,
    var settingsOpen: Boolean = false
)

class ClickGUI(private val moduleManager: ModuleManager) {
    val combatButtons = mutableListOf<ModuleButton>()
    val visualButtons = mutableListOf<ModuleButton>()

    fun rebuild() {
        combatButtons.clear()
        visualButtons.clear()

        moduleManager.all().forEach { module ->
            val button = ModuleButton(module)
            when (module.category) {
                Category.COMBAT -> combatButtons += button
                Category.VISUAL -> visualButtons += button
            }
        }
    }

    fun onLeftClick(button: ModuleButton) {
        button.module.toggle()
    }

    fun onRightClick(button: ModuleButton) {
        button.settingsOpen = !button.settingsOpen
    }
}
