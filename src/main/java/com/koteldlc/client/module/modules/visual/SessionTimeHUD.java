package com.koteldlc.client.module.modules.visual;

import com.koteldlc.client.gui.settings.ModeSetting;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class SessionTimeHUD extends HudElementModule {
    private final Instant startedAt = Instant.now();
    private final ModeSetting format = register(new ModeSetting("format", "compact", List.of("compact", "clock")));

    public SessionTimeHUD() {
        super("SessionTimeHUD", "Время текущей игровой сессии", 8, 58);
    }

    @Override
    protected String buildText() {
        Duration elapsed = Duration.between(startedAt, Instant.now());
        long seconds = elapsed.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long sec = seconds % 60;

        if ("clock".equalsIgnoreCase(format.getValue())) {
            return String.format("%02d:%02d:%02d", hours, minutes, sec);
        }
        return String.format("%dh %02dm", hours, minutes);
    }
}
