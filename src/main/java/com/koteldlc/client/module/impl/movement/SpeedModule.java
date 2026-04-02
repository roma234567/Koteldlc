package com.koteldlc.client.module.impl.movement;

import com.koteldlc.client.config.modules.BypassProfileRepository;
import com.koteldlc.client.gui.settings.ModeSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;

import java.util.List;

public class SpeedModule extends Module {
    public SpeedModule(BypassProfileRepository repository) {
        super("Speed", "Speed с расширяемыми bypass-режимами", -1, Category.MOVEMENT);
        List<String> modes = repository.loadModes("movement", "speed", List.of("Matrix", "FunTime", "Коллизия", "FunTime Elytra", "Старый Matrix"));
        register(new ModeSetting("Режим", modes.getFirst(), modes));
        register(new SliderSetting("Скорость", 1.0, 0.1, 4.0, 0.05));
        register(new SliderSetting("Ускорение", 0.2, 0.0, 1.0, 0.05));
    }
}
