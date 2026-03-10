package com.koteldlc.client.module.modules.combat;

import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.*;

import java.util.List;

public class TeleportAura extends Module {
    public TeleportAura() {
        super("TeleportAura", "Teleports to target and attacks", -1, Category.COMBAT);
        register(new BooleanSetting("enabled", true));
        register(new ModeSetting("mode", "Packet", List.of("Packet", "Timer")));
        register(new SliderSetting("range", 6.0, 3.0, 10.0));
        register(new SliderSetting("delay", 120, 0, 500));
        register(new BooleanSetting("only ground", true));
    }
}
