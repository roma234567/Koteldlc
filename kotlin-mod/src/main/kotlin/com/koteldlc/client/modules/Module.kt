package com.koteldlc.client.modules

import com.google.gson.JsonElement
import com.google.gson.JsonObject

enum class Category {
    COMBAT,
    VISUAL
}

abstract class Module(
    val name: String,
    val category: Category,
    var enabled: Boolean = false
) {
    val settings: LinkedHashMap<String, Any> = linkedMapOf()

    open fun onEnable() {}
    open fun onDisable() {}
    open fun onTick() {}

    fun toggle() {
        enabled = !enabled
        if (enabled) onEnable() else onDisable()
    }

    fun applyEnabled(state: Boolean) {
        if (enabled != state) {
            toggle()
        }
    }

    fun setSetting(key: String, value: Any): Boolean {
        if (!settings.containsKey(key)) return false
        settings[key] = value
        return true
    }

    open fun save(): JsonObject {
        val json = JsonObject()
        json.addProperty("enabled", enabled)
        val settingsJson = JsonObject()
        settings.forEach { (k, v) ->
            when (v) {
                is Boolean -> settingsJson.addProperty(k, v)
                is Number -> settingsJson.addProperty(k, v)
                else -> settingsJson.addProperty(k, v.toString())
            }
        }
        json.add("settings", settingsJson)
        return json
    }

    open fun load(json: JsonObject) {
        if (json.has("enabled")) {
            applyEnabled(json.get("enabled").asBoolean)
        }
        val settingsJson = json.getAsJsonObject("settings") ?: return
        settings.forEach { (key, current) ->
            if (!settingsJson.has(key)) return@forEach
            val value: JsonElement = settingsJson.get(key)
            settings[key] = when (current) {
                is Boolean -> value.asBoolean
                is Int -> value.asInt
                is Double -> value.asDouble
                is Float -> value.asFloat
                is Long -> value.asLong
                else -> value.asString
            }
        }
    }
}
