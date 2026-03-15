package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

import java.util.List;

public class KillAura extends Module {
    public KillAura() {
        super("Attack Aura", "Классическая аура ближнего боя", -1, Category.COMBAT);
        register(new BooleanSetting("enabled", true));
        register(new SliderSetting("Радиус атаки", 3.4, 1.0, 6.0));
        register(new SliderSetting("Радиус обнаружения", 4.2, 1.0, 8.0));
        register(new ModeSetting("Режим цели", "Single", List.of("Single", "Switch", "Multi")));
        register(new ModeSetting("Приоритет", "По дистанции", List.of("По дистанции", "По здоровью", "По броне", "По FOV")));
        register(new BooleanSetting("Только с оружием", false));
        register(new BooleanSetting("Бить только критами", false));
        register(new BooleanSetting("Бить через стены", false));
        register(new BooleanSetting("Синхронизация с TPS", false));
        register(new BooleanSetting("Автоматически ломать щит", true));
        register(new ModeSetting("Тип наведения", "FunTime snap", List.of("Легитный", "Matrix/Vulcan", "FunTime snap")));
        register(new ModeSetting("Коррекция движения", "Сфокусированная", List.of("Свободная", "Преследование", "Сфокусированная")));
        register(new ModeSetting("Отображение цели", "Призраки", List.of("Кольцо", "Призраки", "Ромб", "Не отображать")));
        register(new SliderSetting("Скорость ротации", 18.0, 1.0, 40.0));
    }
}
