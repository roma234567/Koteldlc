package com.koteldlc.client.commands

import com.koteldlc.client.ModuleManager

class StatusCommand(private val moduleManager: ModuleManager) : Command(
    name = "status",
    usage = ".status [module]",
    description = "Показать статус модулей или одного модуля"
) {
    override fun execute(args: List<String>): String {
        if (args.isEmpty()) {
            return moduleManager.all().joinToString(" | ") { "${it.name}:${if (it.enabled) "ON" else "OFF"}" }
        }

        val module = moduleManager.byName(args[0]) ?: return "Модуль не найден: ${args[0]}"
        return "${module.name}: ${if (module.enabled) "ON" else "OFF"}, settings=${module.settings}"
    }
}
