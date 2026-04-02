package com.koteldlc.client.config;

import com.google.gson.*;
import com.koteldlc.client.gui.settings.*;
import com.koteldlc.client.module.Module;
import com.koteldlc.client.module.ModuleManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ConfigManager {
    private static final Path ROOT = Path.of("koteldlc");
    private static final Path FILE = ROOT.resolve("config.json");
    private static final Path AI_KILLAURA_FILE = ROOT.resolve("ai-killaura.json");
    private static final Path PROFILES_DIR = ROOT.resolve("profiles");

    private ModuleManager manager;

    public void attach(ModuleManager manager) {
        this.manager = manager;
    }

    public void save(ModuleManager manager) {
        this.manager = manager;
        saveCurrent(FILE, manager);
        saveAiKillAura(manager);
    }

    public void load(ModuleManager manager) {
        this.manager = manager;
        loadFrom(FILE, manager);
        loadAiKillAura(manager);
    }

    public void saveProfile(String name) {
        if (manager == null || name == null || name.isBlank()) return;
        Path profileFile = PROFILES_DIR.resolve(name + ".json");
        saveCurrent(profileFile, manager);
    }

    public void loadProfile(String name) {
        if (manager == null || name == null || name.isBlank()) return;
        Path profileFile = PROFILES_DIR.resolve(name + ".json");
        loadFrom(profileFile, manager);
    }

    public List<String> listProfiles() {
        if (!Files.exists(PROFILES_DIR)) return List.of();
        List<String> names = new ArrayList<>();
        try (Stream<Path> stream = Files.list(PROFILES_DIR)) {
            stream.filter(path -> path.getFileName().toString().endsWith(".json"))
                    .map(path -> path.getFileName().toString().replace(".json", ""))
                    .sorted()
                    .forEach(names::add);
        } catch (IOException ignored) { }
        return names;
    }

    private void saveCurrent(Path file, ModuleManager manager) {
        JsonObject root = new JsonObject();
        for (Module module : manager.getModules()) {
            root.add(module.getName(), serializeModule(module));
        }

        try {
            Files.createDirectories(file.getParent());
            Files.writeString(file, new GsonBuilder().setPrettyPrinting().create().toJson(root));
        } catch (IOException ignored) { }
    }

    private void loadFrom(Path file, ModuleManager manager) {
        if (!Files.exists(file)) return;
        try {
            JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
            for (Module module : manager.getModules()) {
                if (!root.has(module.getName())) continue;
                applyModule(module, root.getAsJsonObject(module.getName()));
            }
        } catch (Exception ignored) { }
    }

    private JsonObject serializeModule(Module module) {
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
        return obj;
    }

    private void applyModule(Module module, JsonObject obj) {
        if (obj.has("enabled")) {
            module.setToggled(obj.get("enabled").getAsBoolean());
        }
        if (obj.has("key")) {
            module.setKey(obj.get("key").getAsInt());
        }

        if (!obj.has("settings")) {
            return;
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

    private void saveAiKillAura(ModuleManager manager) {
        Module aiKillAura = manager.getByName("AI KillAura");
        if (aiKillAura == null) {
            return;
        }
        try {
            Files.createDirectories(AI_KILLAURA_FILE.getParent());
            Files.writeString(AI_KILLAURA_FILE, new GsonBuilder().setPrettyPrinting().create().toJson(serializeModule(aiKillAura)));
        } catch (IOException ignored) { }
    }

    private void loadAiKillAura(ModuleManager manager) {
        if (!Files.exists(AI_KILLAURA_FILE)) {
            return;
        }
        Module aiKillAura = manager.getByName("AI KillAura");
        if (aiKillAura == null) {
            return;
        }

        try {
            JsonObject aiRoot = JsonParser.parseString(Files.readString(AI_KILLAURA_FILE)).getAsJsonObject();
            applyModule(aiKillAura, aiRoot);
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
