package com.koteldlc.client.commands

import com.koteldlc.client.ModuleManager

class ToggleCommand(private val moduleManager: ModuleManager) : Command(
    name = "toggle",
    usage = ".toggle <module>",
    description = "Вкл/выкл модуль"
) {
    override fun execute(args: List<String>): String {
        if (args.isEmpty()) return "Usage: $usage"
        val module = moduleManager.byName(args[0]) ?: return "Модуль не найден: ${args[0]}"
        module.toggle()
        return "${module.name} => ${if (module.enabled) "ON" else "OFF"}"
    }
}
