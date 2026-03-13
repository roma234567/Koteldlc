package com.koteldlc.client

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.koteldlc.client.gui.ClickGUI
import com.koteldlc.client.gui.HudOverlay
import com.koteldlc.client.modules.Category
import com.koteldlc.client.modules.combat.KillAura
import java.nio.file.Files
import java.nio.file.Path

class ConfigManager(
    private val moduleManager: ModuleManager,
    private val clickGUI: ClickGUI,
    private val hudOverlay: HudOverlay
) {
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val file: Path = Path.of("kotlin-mod", "config.json")

    fun save() {
        val root = JsonObject()
        val modules = JsonObject()

        moduleManager.all().forEach { module ->
            modules.add(module.name, module.save())
            if (module is KillAura) {
                root.add("killAuraAI", module.getAiModel().save())
            }
        }
        root.add("modules", modules)

        val gui = JsonObject()
        gui.addProperty("x", clickGUI.rootPanel.x)
        gui.addProperty("y", clickGUI.rootPanel.y)
        gui.addProperty("selectedCategory", clickGUI.selectedCategory.name)
        root.add("clickGui", gui)

        val hud = JsonObject()
        hudOverlay.savePositions().forEach { (name, pos) ->
            val obj = JsonObject()
            obj.addProperty("x", pos.first)
            obj.addProperty("y", pos.second)
            hud.add(name, obj)
        }
        root.add("hud", hud)

        Files.createDirectories(file.parent)
        Files.writeString(file, gson.toJson(root))
    }

    fun load() {
        if (!Files.exists(file)) return
        val root = JsonParser.parseString(Files.readString(file)).asJsonObject

        val modulesJson = root.getAsJsonObject("modules")
        if (modulesJson != null) {
            moduleManager.all().forEach { module ->
                if (modulesJson.has(module.name)) {
                    module.load(modulesJson.getAsJsonObject(module.name))
                }
                if (module is KillAura && root.has("killAuraAI")) {
                    module.getAiModel().load(root.getAsJsonObject("killAuraAI"))
                }
            }
        }

        root.getAsJsonObject("clickGui")?.let { gui ->
            if (gui.has("x")) clickGUI.rootPanel.x = gui.get("x").asInt
            if (gui.has("y")) clickGUI.rootPanel.y = gui.get("y").asInt
            if (gui.has("selectedCategory")) {
                clickGUI.selectedCategory = runCatching {
                    Category.valueOf(gui.get("selectedCategory").asString)
                }.getOrDefault(Category.COMBAT)
            }
        }

        root.getAsJsonObject("hud")?.let { hud ->
            val map = mutableMapOf<String, Pair<Int, Int>>()
            listOf("potions", "staffs", "keybinds", "watermark").forEach { name ->
                if (hud.has(name)) {
                    val obj = hud.getAsJsonObject(name)
                    map[name] = obj.get("x").asInt to obj.get("y").asInt
                }
            }
            hudOverlay.loadPositions(map)
        }
    }
}
