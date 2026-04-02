package com.koteldlc.client.module.impl.movement;

import com.koteldlc.client.config.modules.BypassProfileRepository;
import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.ModeSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;

import java.util.List;

public class FlyModule extends Module {
    public FlyModule(BypassProfileRepository repository) {
        super("Fly", "Fly с кастомными bypass-профилями", -1, Category.MOVEMENT);
        List<String> modes = repository.loadModes("movement", "fly", List.of("Grimm", "Matrix", "FunTime", "Slot AC", "Vanilla"));
        register(new ModeSetting("Режим", modes.getFirst(), modes));
        register(new SliderSetting("Горизонтальная скорость", 1.0, 0.1, 5.0, 0.05));
        register(new SliderSetting("Вертикальная скорость", 1.0, 0.1, 5.0, 0.05));
        register(new BooleanSetting("Анти kick", true));
        repository.registerDynamicSettings(this, "movement", "fly");
        repository.applyValueOverrides(this, "movement", "fly");
    }
}
