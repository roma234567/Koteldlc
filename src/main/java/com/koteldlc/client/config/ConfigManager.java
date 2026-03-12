package com.koteldlc.client.config;

import com.google.gson.*;
import com.koteldlc.client.gui.settings.*;
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
                Object value = setting.getValue();
                if (value instanceof Number number) {
                    settings.addProperty(setting.getName(), number);
                } else if (value instanceof Boolean bool) {
                    settings.addProperty(setting.getName(), bool);
                } else {
                    settings.addProperty(setting.getName(), String.valueOf(value));
                }
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

                if (!obj.has("settings")) {
                    continue;
                }
                JsonObject settingsObj = obj.getAsJsonObject("settings");
                for (Setting<?> setting : module.getSettings()) {
                    if (!settingsObj.has(setting.getName())) {
                        continue;
                    }
                    JsonElement value = settingsObj.get(setting.getName());
                    applyValue(setting, value);
                }
            }
        } catch (Exception ignored) { }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void applyValue(Setting setting, JsonElement value) {
        if (setting instanceof BooleanSetting) {
            setting.setValue(value.getAsBoolean());
        } else if (setting instanceof ColorSetting) {
            setting.setValue(value.getAsInt());
        } else if (setting instanceof SliderSetting) {
            setting.setValue(value.getAsDouble());
        } else if (setting instanceof ModeSetting) {
            setting.setValue(value.getAsString());
        }
    }
}
