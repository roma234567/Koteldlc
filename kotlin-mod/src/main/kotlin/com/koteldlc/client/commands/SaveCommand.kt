package com.koteldlc.client.commands

class SaveCommand(private val saveAction: () -> Unit) : Command(
    name = "save",
    usage = ".save",
    description = "Сохранить текущий config.json"
) {
    override fun execute(args: List<String>): String {
        saveAction()
        return "Конфиг сохранён"
    }
}
