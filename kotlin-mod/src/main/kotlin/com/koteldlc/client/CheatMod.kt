package com.koteldlc.client

import com.koteldlc.client.commands.*
import com.koteldlc.client.commands.ai.AICommand
import com.koteldlc.client.gui.ClickGUI
import com.koteldlc.client.gui.HudOverlay

object CheatMod {
    val moduleManager = ModuleManager()
    val clickGui = ClickGUI(moduleManager)
    val hudOverlay = HudOverlay()
    val configManager = ConfigManager(moduleManager, clickGui, hudOverlay)
    val commandManager = CommandManager(moduleManager)

    fun init() {
        commandManager.register(HelpCommand())
        commandManager.register(ToggleCommand(moduleManager))
        commandManager.register(SetCommand(moduleManager))
        commandManager.register(StatusCommand(moduleManager))
        commandManager.register(BindCommand(moduleManager))
        commandManager.register(AICommand(moduleManager))
        commandManager.register(SaveCommand { configManager.save() })
        commandManager.register(GuiCommand {
            clickGui.resetLayout()
            hudOverlay.resetPositions()
            configManager.save()
        })
        commandManager.register(FindCommand(moduleManager))

        clickGui.rebuild()
        configManager.load()
    }

    fun onChatMessage(message: String): Boolean {
        // true => сообщение обработано как команда, в чат сервера не отправляем
        if (!message.startsWith('.')) return false
        return commandManager.execute(message)
    }
}
