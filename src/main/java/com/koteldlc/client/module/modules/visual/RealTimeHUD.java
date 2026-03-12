package com.koteldlc.client.module.modules.visual;

import com.koteldlc.client.gui.settings.ModeSetting;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RealTimeHUD extends HudElementModule {
    private final ModeSetting format = register(new ModeSetting("format", "24h", List.of("24h", "12h")));

    public RealTimeHUD() {
        super("RealTimeHUD", "Реальное системное время", 8, 44);
    }

    @Override
    protected String buildText() {
        LocalTime now = LocalTime.now();
        if ("12h".equalsIgnoreCase(format.getValue())) {
            return now.format(DateTimeFormatter.ofPattern("h:mm a"));
        }
        return now.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
