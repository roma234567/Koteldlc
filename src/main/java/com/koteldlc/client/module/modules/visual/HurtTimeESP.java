package com.koteldlc.client.module.modules.visual;

import com.koteldlc.client.gui.settings.SliderSetting;

public class HurtTimeESP extends BaseEspModule {
    private final SliderSetting durationSec = register(new SliderSetting("duration_sec", 1.0, 0.5, 3.0));

    public HurtTimeESP() {
        super("HurtTimeESP", "Подсветка недавно получивших урон игроков", 0xFFFF3030);
        blink.setValue(true);
    }

    public double getDurationSec() {
        return durationSec.getValue();
    }
}
