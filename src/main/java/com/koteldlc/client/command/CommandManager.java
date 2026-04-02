package com.koteldlc.client.command;

import com.koteldlc.client.config.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.List;

public class CommandManager {
    private final ConfigManager configManager;

    public CommandManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public boolean handle(String message) {
        if (message == null || !message.startsWith(".")) {
            return false;
        }

        String[] parts = message.trim().split("\\s+");
        if (parts.length < 2 || !parts[0].equalsIgnoreCase(".cfg")) {
            return false;
        }

        if (parts[1].equalsIgnoreCase("list")) {
            List<String> names = configManager.listProfiles();
            send("CFG: " + (names.isEmpty() ? "пока нет профилей" : String.join(", ", names)));
            return true;
        }

        if (parts.length < 3) {
            send("Используй: .cfg save <name> | .cfg load <name> | .cfg list");
            return true;
        }

        String profile = parts[2].trim();
        switch (parts[1].toLowerCase()) {
            case "save" -> {
                configManager.saveProfile(profile);
                send("Профиль сохранен: " + profile);
                return true;
            }
            case "load" -> {
                configManager.loadProfile(profile);
                send("Профиль загружен: " + profile);
                return true;
            }
            default -> {
                send("Неизвестная команда cfg: " + parts[1]);
                return true;
            }
        }
    }

    private void send(String text) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc != null && mc.player != null) {
            mc.player.sendMessage(Text.literal("[KotelDLC] " + text), false);
        }
    }
}
