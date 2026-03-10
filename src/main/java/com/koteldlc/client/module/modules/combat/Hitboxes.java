package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

public class Hitboxes extends Module {
    public Hitboxes() {
        super("Hitboxes", "Expands entity hitboxes", -1, Category.COMBAT);
        register(new BooleanSetting("enabled", true));
        register(new SliderSetting("size", 0.5, 0.1, 3.0));
        register(new BooleanSetting("through walls", false));
    }
}
