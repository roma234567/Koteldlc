package com.koteldlc.client.commands

class GuiCommand(private val resetAction: () -> Unit) : Command(
    name = "gui",
    usage = ".gui <reset>",
    description = "Управление GUI/HUD (сейчас: reset)"
) {
    override fun execute(args: List<String>): String {
        if (args.isEmpty()) return "Usage: $usage"
        return when (args[0].lowercase()) {
            "reset" -> {
                resetAction()
                "GUI/HUD позиции сброшены"
            }
            else -> "Usage: $usage"
        }
    }
}
