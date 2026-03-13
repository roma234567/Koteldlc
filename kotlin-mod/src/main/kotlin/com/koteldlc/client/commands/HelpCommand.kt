package com.koteldlc.client.commands

class HelpCommand : Command(
    name = "help",
    usage = ".help",
    description = "Показать список команд"
) {
    override fun execute(args: List<String>): String {
        return "Доступно: .help .toggle .set .status .bind .ai .save .gui reset .find"
    }
}
