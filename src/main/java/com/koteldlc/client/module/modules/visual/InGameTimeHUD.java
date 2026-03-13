package com.koteldlc.client.module.modules.visual;

import net.minecraft.client.MinecraftClient;

public class InGameTimeHUD extends HudElementModule {
    public InGameTimeHUD() {
        super("InGameTimeHUD", "Игровое время мира", 8, 30);
    }

    @Override
    protected String buildText() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null) {
            return "Day 1 | 06:00";
        }
        long dayTime = client.world.getTimeOfDay() % 24000L;
        long day = client.world.getTimeOfDay() / 24000L + 1;
        long hours = (dayTime / 1000 + 6) % 24;
        long minutes = Math.round((dayTime % 1000) * 60.0 / 1000.0);
        String phase = (hours >= 6 && hours < 18) ? "Day " + day : "Night";
        return String.format("%s | %02d:%02d", phase, hours, minutes % 60);
    }
}
