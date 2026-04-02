package com.koteldlc.client.module.impl.combat;

import com.koteldlc.client.config.modules.BypassProfileRepository;
import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.ModeSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;

import java.util.List;

public class AttackAuraModule extends Module {
    public AttackAuraModule(BypassProfileRepository repository) {
        super("Attack Aura", "Настраиваемая KillAura с bypass-режимами", -1, Category.COMBAT);

        register(new SliderSetting("Радиус атаки", 3.4, 1.0, 6.0, 0.1));
        register(new SliderSetting("Радиус обнаружения", 4.2, 1.0, 8.0, 0.1));

        register(new BooleanSetting("Игроков", true));
        register(new BooleanSetting("Мобов", false));
        register(new BooleanSetting("Жителей", false));
        register(new BooleanSetting("Невидимых", true));
        register(new BooleanSetting("Голых", true));

        register(new ModeSetting("Приоритет", "По дистанции", List.of("По дистанции", "По здоровью", "По броне", "По FOV", "По всем сразу")));
        register(new ModeSetting("Коррекция движения", "Сфокусированная", List.of("Свободная", "Преследование", "Сфокусированная")));
        register(new ModeSetting("Отображение цели", "Призраки", List.of("Кольцо", "Призраки", "Ромб", "Не отображать")));
        register(new ModeSetting("Спринт", "Легитный", List.of("Легитный", "Funtime", "Быстрый")));

        List<String> rotationModes = repository.loadModes("combat", "killaura", List.of("Резкая", "Плавная", "Snap", "FunTime snap"));
        register(new ModeSetting("Тип наведения", rotationModes.getFirst(), rotationModes));

        register(new BooleanSetting("Только с оружием", false));
        register(new BooleanSetting("Бить только критами", false));
        register(new BooleanSetting("Бить через стены", false));
        register(new BooleanSetting("Синхронизация с TPS", false));
        register(new BooleanSetting("Автоматически ломать щит", true));

        register(new BooleanSetting("Открыт контейнер", true));
        register(new BooleanSetting("Летишь на элитрах", true));
        register(new BooleanSetting("Используешь еду", true));

        register(new SliderSetting("Скорость ротации", 18.0, 1.0, 40.0, 0.5));
        register(new SliderSetting("Pitch множитель", 1.0, 0.0, 2.0, 0.05));
        register(new SliderSetting("Yaw множитель", 1.0, 0.0, 2.0, 0.05));
    }
}
