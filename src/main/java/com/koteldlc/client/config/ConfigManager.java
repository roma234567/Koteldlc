package com.koteldlc.client.config;

import com.google.gson.*;
import com.koteldlc.client.gui.settings.Setting;
import com.koteldlc.client.module.Module;
import com.koteldlc.client.module.ModuleManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private static final Path FILE = Path.of("koteldlc", "config.json");

    public void save(ModuleManager manager) {
        JsonObject root = new JsonObject();
        for (Module module : manager.getModules()) {
            JsonObject obj = new JsonObject();
            obj.addProperty("enabled", module.isToggled());
            obj.addProperty("key", module.getKey());
            JsonObject settings = new JsonObject();
            for (Setting<?> setting : module.getSettings()) {
                settings.addProperty(setting.getName(), String.valueOf(setting.getValue()));
            }
            obj.add("settings", settings);
            root.add(module.getName(), obj);
        }
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, new GsonBuilder().setPrettyPrinting().create().toJson(root));
        } catch (IOException ignored) { }
    }

    public void load(ModuleManager manager) {
        if (!Files.exists(FILE)) return;
        try {
            JsonObject root = JsonParser.parseString(Files.readString(FILE)).getAsJsonObject();
            for (Module module : manager.getModules()) {
                if (!root.has(module.getName())) continue;
                JsonObject obj = root.getAsJsonObject(module.getName());
                module.setToggled(obj.get("enabled").getAsBoolean());
                module.setKey(obj.get("key").getAsInt());
            }
        } catch (Exception ignored) { }
    }
}
