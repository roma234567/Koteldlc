package com.koteldlc.client

import com.koteldlc.client.modules.Module
import com.koteldlc.client.modules.combat.KillAura
import com.koteldlc.client.modules.visual.*

class ModuleManager {
    private val modules = linkedMapOf<String, Module>()

    init {
        register(KillAura()) // новая версия
        register(HurtTimeESP())
        register(InvulnerableESP())
        register(SneakingESP())
        register(GlidingESP())
        register(SleepingESP())
        register(TargetESP())
    }

    fun register(module: Module) {
        modules[module.name.lowercase()] = module
    }

    fun all(): List<Module> = modules.values.toList()

    fun byName(name: String): Module? = modules[name.lowercase()]
}
