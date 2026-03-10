package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

public class TargetHUD extends Module {
    public TargetHUD() {
        super("TargetHUD", "Target information overlay", -1, Category.RENDER);
        register(new BooleanSetting("enabled", true));
        register(new SliderSetting("x", 20, 0, 1920));
        register(new SliderSetting("y", 20, 0, 1080));
        register(new BooleanSetting("show armor", true));
        register(new BooleanSetting("show weapon", true));
        register(new ColorSetting("background", 0x99000000));
        register(new ColorSetting("text color", 0xFFFFFFFF));
    }
}
