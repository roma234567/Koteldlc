package com.koteldlc.client.module.impl.combat;

import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;

public class AutoTotemModule extends Module {
    public AutoTotemModule() {
        super("Auto Totem", "Авто-тотем с условиями как на скрине", -1, Category.COMBAT);
        register(new BooleanSetting("Обход Matrix", true));
        register(new SliderSetting("Здоровье", 4.0, 1.0, 20.0, 0.5));
        register(new SliderSetting("Здоровье на элитре", 10.0, 1.0, 20.0, 0.5));
        register(new BooleanSetting("Не брать тотем если шар", true));
        register(new BooleanSetting("Не брать тотем когда ешь", false));
        register(new BooleanSetting("Вагонетка с бомбой", false));
        register(new BooleanSetting("Крипер", false));
        register(new BooleanSetting("THT", false));
        register(new BooleanSetting("Трезубец", false));
        register(new BooleanSetting("Падаешь", false));
        register(new BooleanSetting("Кристалл", true));
        register(new SliderSetting("Радиус проверки", 4.0, 1.0, 8.0, 0.1));
    }
}
