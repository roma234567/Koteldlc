package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

public class JumpCircles extends Module {
    public JumpCircles() {
        super("JumpCircles", "Renders jump circles", -1, Category.RENDER);
        register(new BooleanSetting("enabled", true));
        register(new ColorSetting("color", 0xAA55FFFF));
        register(new SliderSetting("radius", 2.0, 0.5, 5.0));
        register(new SliderSetting("duration", 0.6, 0.1, 2.0));
        register(new BooleanSetting("fade out", true));
    }
}
