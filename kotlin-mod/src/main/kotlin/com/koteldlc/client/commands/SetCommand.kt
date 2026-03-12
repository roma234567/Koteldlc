package com.koteldlc.client.commands

import com.koteldlc.client.ModuleManager

class SetCommand(private val moduleManager: ModuleManager) : Command(
    name = "set",
    usage = ".set <module> <setting> <value>",
    description = "Изменить значение настройки модуля"
) {
    override fun execute(args: List<String>): String {
        if (args.size < 3) return "Usage: $usage"

        val module = moduleManager.byName(args[0]) ?: return "Модуль не найден: ${args[0]}"
        val key = args[1]
        val rawValue = args.drop(2).joinToString(" ")

        val current = module.settings[key] ?: return "Настройка не найдена: $key"
        val parsed: Any = when (current) {
            is Boolean -> rawValue.equals("true", true)
            is Int -> rawValue.toIntOrNull() ?: return "Ожидалось Int"
            is Double -> rawValue.toDoubleOrNull() ?: return "Ожидалось Double"
            else -> rawValue
        }

        module.settings[key] = parsed
        return "${module.name}: $key = $parsed"
    }
}
