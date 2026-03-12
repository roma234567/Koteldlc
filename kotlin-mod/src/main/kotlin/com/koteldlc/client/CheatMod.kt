package com.koteldlc.client

import com.koteldlc.client.commands.*
import com.koteldlc.client.commands.ai.AICommand

object CheatMod {
    val moduleManager = ModuleManager()
    val configManager = ConfigManager(moduleManager)
    val commandManager = CommandManager(moduleManager)

    fun init() {
        commandManager.register(HelpCommand())
        commandManager.register(ToggleCommand(moduleManager))
        commandManager.register(SetCommand(moduleManager))
        commandManager.register(StatusCommand(moduleManager))
        commandManager.register(BindCommand(moduleManager))
        commandManager.register(AICommand(moduleManager))

        configManager.load()
    }

    fun onChatMessage(message: String): Boolean {
        // true => сообщение обработано как команда, в чат сервера не отправляем
        if (!message.startsWith('.')) return false
        return commandManager.execute(message)
    }
}
