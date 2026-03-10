package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

import java.util.List;

public class TriggerBot extends Module {
    public TriggerBot() {
        super("TriggerBot", "Automatically attacks targets in crosshair", -1, Category.COMBAT);
        register(new BooleanSetting("enabled", true));
        register(new SliderSetting("delay", 100, 0, 500));
        register(new ModeSetting("target mode", "Single", List.of("Single", "Multi")));
        register(new BooleanSetting("weapon only", true));
        register(new BooleanSetting("through walls", false));
        register(new BooleanSetting("in air", true));
    }
}
