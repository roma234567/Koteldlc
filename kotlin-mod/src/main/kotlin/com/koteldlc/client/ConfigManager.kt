package com.koteldlc.client

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.koteldlc.client.commands.ai.AIModel
import com.koteldlc.client.modules.combat.KillAura
import java.nio.file.Files
import java.nio.file.Path

class ConfigManager(
    private val moduleManager: ModuleManager
) {
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val file: Path = Path.of("kotlin-mod", "config.json")

    fun save() {
        val root = JsonObject()
        val modules = JsonObject()

        moduleManager.all().forEach { module ->
            modules.add(module.name, module.save())

            if (module is KillAura) {
                val aiJson = module.getAiModel().save()
                root.add("killAuraAI", aiJson)
            }
        }

        root.add("modules", modules)
        Files.createDirectories(file.parent)
        Files.writeString(file, gson.toJson(root))
    }

    fun load() {
        if (!Files.exists(file)) return
        val root = JsonParser.parseString(Files.readString(file)).asJsonObject
        val modulesJson = root.getAsJsonObject("modules") ?: return

        moduleManager.all().forEach { module ->
            if (modulesJson.has(module.name)) {
                module.load(modulesJson.getAsJsonObject(module.name))
            }
            if (module is KillAura && root.has("killAuraAI")) {
                module.getAiModel().load(root.getAsJsonObject("killAuraAI"))
            }
        }
    }
}
