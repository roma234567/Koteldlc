package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

import java.util.List;

public class KillAura extends Module {
    public KillAura() {
        super("KillAura", "Automatically attacks nearby entities", -1, Category.COMBAT);
        register(new BooleanSetting("enabled", true));
        register(new SliderSetting("range", 4.2, 3.0, 6.0));
        register(new ModeSetting("target mode", "Single", List.of("Single", "Switch", "Multi")));
        register(new ModeSetting("priority", "Distance", List.of("Health", "Distance", "Angle", "Armor")));
        register(new BooleanSetting("through walls", false));
        register(new BooleanSetting("auto block", false));
        register(new BooleanSetting("only criticals", false));
        register(new ModeSetting("rotation mode", "Smooth", List.of("None", "Smooth", "Snap", "Circle")));
        register(new SliderSetting("smooth speed", 8.0, 1.0, 20.0));
        register(new SliderSetting("circle radius", 120.0, 20.0, 300.0));
        register(new SliderSetting("attack speed", 12.0, 0.0, 20.0));
        register(new BooleanSetting("randomize delay", true));
    }
}
