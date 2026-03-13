package com.koteldlc.client.commands

import com.koteldlc.client.ModuleManager

class BindCommand(private val moduleManager: ModuleManager) : Command(
    name = "bind",
    usage = ".bind <module> <key>",
    description = "Назначить клавишу модулю"
) {
    private val binds = mutableMapOf<String, String>()

    override fun execute(args: List<String>): String {
        if (args.size < 2) return "Usage: $usage"
        val module = moduleManager.byName(args[0]) ?: return "Модуль не найден: ${args[0]}"
        val key = args[1].uppercase()
        binds[module.name.lowercase()] = key
        return "${module.name} bind => $key"
    }
}
