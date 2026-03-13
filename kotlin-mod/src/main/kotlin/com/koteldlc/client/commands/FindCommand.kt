package com.koteldlc.client.commands

import com.koteldlc.client.ModuleManager

class FindCommand(private val moduleManager: ModuleManager) : Command(
    name = "find",
    usage = ".find <query>",
    description = "Поиск модулей по части названия"
) {
    override fun execute(args: List<String>): String {
        if (args.isEmpty()) return "Usage: $usage"
        val q = args.joinToString(" ").lowercase()
        val found = moduleManager.all().filter { it.name.lowercase().contains(q) }
        return if (found.isEmpty()) {
            "Ничего не найдено"
        } else {
            "Найдено: " + found.joinToString(", ") { it.name }
        }
    }
}
