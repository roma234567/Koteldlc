package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

import java.util.List;

public class ESP extends Module {
    public ESP() {
        super("ESP", "Entity highlighting", -1, Category.RENDER);
        register(new BooleanSetting("enabled", true));
        register(new ModeSetting("mode", "Box", List.of("Box", "Outline", "Name", "Health", "Armor")));
        register(new ColorSetting("player color", 0xFF00FF00));
        register(new ColorSetting("mob color", 0xFFFF0000));
        register(new ColorSetting("animal color", 0xFF00AAFF));
        register(new BooleanSetting("through walls", true));
        register(new SliderSetting("render distance", 128, 10, 256));
    }
}
