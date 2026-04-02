package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;

import java.util.List;

public class AIKillAura extends Module {
    public AIKillAura() {
        super("AI KillAura", "Умная аура с профилями и расширенной логикой", -1, Category.COMBAT);

        register(new SliderSetting("Радиус атаки", 3.4, 1.0, 6.0));
        register(new SliderSetting("Радиус обнаружения", 4.2, 1.0, 8.0));

        register(new BooleanSetting("Игроков", true));
        register(new BooleanSetting("Мобов", false));
        register(new BooleanSetting("Жителей", false));
        register(new BooleanSetting("Невидимых", true));
        register(new BooleanSetting("Голых", true));

        register(new ModeSetting("Приоритет", "По дистанции", List.of("По дистанции", "По здоровью", "По броне", "По FOV", "По всем сразу")));
        register(new ModeSetting("Тип наведения", "FunTime snap", List.of("Grim/Vulcan", "HolyWorld", "Matrix/Vulcan", "Легитный", "SpookyTime", "Legit snap", "FunTime snap")));
        register(new ModeSetting("Коррекция движения", "Сфокусированная", List.of("Свободная", "Преследование", "Сфокусированная")));
        register(new ModeSetting("Отображение цели", "Призраки", List.of("Кольцо", "Призраки", "Ромб", "Не отображать")));
        register(new ModeSetting("Спринт", "Легитный", List.of("Легитный", "Funtime", "Быстрый")));

        register(new BooleanSetting("Только с оружием", false));
        register(new BooleanSetting("Бить только критами", false));
        register(new BooleanSetting("Бить через стены", false));
        register(new BooleanSetting("Синхронизация с TPS", false));
        register(new BooleanSetting("Автоматически ломать щит", true));

        register(new BooleanSetting("Открыт контейнер", true));
        register(new BooleanSetting("Летишь на элитрах", true));
        register(new BooleanSetting("Используешь еду", true));

        register(new SliderSetting("Скорость ротации", 18.0, 1.0, 40.0));
        register(new SliderSetting("Случайность клика", 0.15, 0.0, 1.0));
    }
}
