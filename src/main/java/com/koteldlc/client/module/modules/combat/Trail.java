package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

import java.util.List;

public class Trail extends Module {
    public Trail() {
        super("Trail", "Leaves a visual trail", -1, Category.RENDER);
        register(new BooleanSetting("enabled", true));
        register(new ModeSetting("mode", "Particles", List.of("Particles", "Boxes", "Lines")));
        register(new ColorSetting("color", 0xFFFFAA33));
        register(new SliderSetting("max points", 80, 10, 200));
        register(new BooleanSetting("fade", true));
    }
}
