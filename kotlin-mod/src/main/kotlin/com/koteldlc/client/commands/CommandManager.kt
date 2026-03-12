package com.koteldlc.client.commands

import com.koteldlc.client.ModuleManager

class CommandManager(private val moduleManager: ModuleManager) {
    private val commands = linkedMapOf<String, Command>()

    fun register(command: Command) {
        commands[command.name.lowercase()] = command
    }

    fun all(): List<Command> = commands.values.toList()

    fun execute(input: String): Boolean {
        val raw = input.trim()
        if (!raw.startsWith('.')) return false

        val parts = raw.drop(1).split(' ').filter { it.isNotBlank() }
        if (parts.isEmpty()) return false

        val cmdName = parts.first().lowercase()
        val command = commands[cmdName] ?: return false
        val args = parts.drop(1)
        val response = command.execute(args)

        // Тут можно вывести в in-game чат.
        println("[Command] $response")
        return true
    }
}
