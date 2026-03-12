package com.koteldlc.client.module.modules.visual;

import net.minecraft.client.MinecraftClient;

public class ServerTimeHUD extends HudElementModule {
    public ServerTimeHUD() {
        super("ServerTimeHUD", "Серверное время (если доступно)", 8, 72);
    }

    @Override
    protected String buildText() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null) {
            return "Server: N/A";
        }
        long dayTime = client.world.getTimeOfDay() % 24000L;
        long hours = (dayTime / 1000 + 6) % 24;
        long minutes = Math.round((dayTime % 1000) * 60.0 / 1000.0);
        return String.format("Server: %02d:%02d", hours, minutes % 60);
    }
}
