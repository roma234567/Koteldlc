package com.koteldlc.client.module.modules.movement;

import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.module.*;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", "NoFall movement module", -1, Category.MOVEMENT);
        register(new BooleanSetting("enabled", true));
    }
}
