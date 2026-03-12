package com.koteldlc.client.commands.ai

import com.koteldlc.client.ModuleManager
import com.koteldlc.client.commands.Command
import com.koteldlc.client.modules.combat.KillAura

class AICommand(private val moduleManager: ModuleManager) : Command(
    name = "ai",
    usage = ".ai <learn/reset/stats>",
    description = "Управление AI моделью KillAura"
) {
    override fun execute(args: List<String>): String {
        val ka = moduleManager.byName("killaura") as? KillAura ?: return "KillAura не найден"
        val model = ka.getAiModel()

        if (args.isEmpty()) return "Usage: $usage"
        return when (args[0].lowercase()) {
            "learn" -> {
                model.learn(observedDelay = 75.0, learningSpeed = (ka.settings["learningSpeed"] as Double))
                "AI learn OK: ${model.stats()}"
            }
            "reset" -> {
                model.reset()
                "AI reset OK"
            }
            "stats" -> model.stats()
            else -> "Usage: $usage"
        }
    }
}
