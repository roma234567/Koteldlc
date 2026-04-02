package com.koteldlc.client.config.modules;

import com.google.gson.*;
import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.ColorSetting;
import com.koteldlc.client.gui.settings.ModeSetting;
import com.koteldlc.client.gui.settings.Setting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Module;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BypassProfileRepository {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Path root = Path.of("koteldlc", "modules", "impl");

    public List<String> loadModes(String category, String module, List<String> fallbackModes) {
        Path file = root.resolve(category).resolve(module + ".json");
        ensureTemplate(file, fallbackModes);

        JsonObject json = readJson(file);
        List<String> result = new ArrayList<>(fallbackModes);
        JsonArray modes = json.getAsJsonArray("modes");
        if (modes != null) {
            for (JsonElement element : modes) {
                if (element.isJsonPrimitive()) {
                    String mode = element.getAsString();
                    if (!mode.isBlank() && !result.contains(mode)) {
                        result.add(mode);
                    }
                }
            }
        }
        return result;
    }

    public void registerDynamicSettings(Module module, String category, String moduleFileName) {
        Path file = root.resolve(category).resolve(moduleFileName + ".json");
        ensureTemplate(file, List.of());
        JsonObject json = readJson(file);
        JsonArray settings = json.getAsJsonArray("settings");
        if (settings == null) {
            return;
        }

        for (JsonElement element : settings) {
            if (!element.isJsonObject()) continue;
            JsonObject settingObj = element.getAsJsonObject();
            String type = getString(settingObj, "type", "").toLowerCase();
            String name = getString(settingObj, "name", "");
            if (name.isBlank()) continue;

            Setting<?> setting = switch (type) {
                case "slider" -> new SliderSetting(
                        name,
                        getDouble(settingObj, "default", 1.0),
                        getDouble(settingObj, "min", 0.0),
                        getDouble(settingObj, "max", 10.0),
                        getDouble(settingObj, "step", 0.1)
                );
                case "boolean" -> new BooleanSetting(name, getBoolean(settingObj, "default", false));
                case "mode" -> {
                    List<String> options = new ArrayList<>();
                    JsonArray arr = settingObj.getAsJsonArray("options");
                    if (arr != null) {
                        for (JsonElement option : arr) {
                            if (option.isJsonPrimitive()) options.add(option.getAsString());
                        }
                    }
                    if (options.isEmpty()) options.add("Default");
                    String def = getString(settingObj, "default", options.getFirst());
                    yield new ModeSetting(name, def, options);
                }
                case "color" -> new ColorSetting(name, getInt(settingObj, "default", 0xFFFFFFFF));
                default -> null;
            };
            if (setting != null) {
                module.expose(setting);
            }
        }
    }


    public void applyValueOverrides(Module module, String category, String moduleFileName) {
        Path file = root.resolve(category).resolve(moduleFileName + ".json");
        ensureTemplate(file, List.of());
        JsonObject json = readJson(file);
        JsonObject values = json.getAsJsonObject("values");
        if (values == null) return;

        for (Setting<?> setting : module.getSettings()) {
            String key = normalize(setting.getName());
            if (!values.has(key)) continue;
            JsonElement value = values.get(key);
            try {
                if (setting instanceof SliderSetting slider) {
                    slider.setValue(value.getAsDouble());
                } else if (setting instanceof BooleanSetting bool) {
                    bool.setValue(value.getAsBoolean());
                } else if (setting instanceof ModeSetting mode) {
                    String selected = value.getAsString();
                    mode.addMode(selected);
                    mode.setValue(selected);
                } else if (setting instanceof ColorSetting color) {
                    color.setValue(value.getAsInt());
                }
            } catch (Exception ignored) {}
        }
    }

    private String normalize(String value) {
        return value.toLowerCase().replaceAll("[^a-z0-9]+", "_").replaceAll("_+", "_").replaceAll("^_|_$", "");
    }

    private JsonObject readJson(Path file) {
        if (!Files.exists(file)) return new JsonObject();
        try {
            return JsonParser.parseString(Files.readString(file)).getAsJsonObject();
        } catch (Exception ignored) {
            return new JsonObject();
        }
    }

    private String getString(JsonObject o, String key, String def) {
        return o.has(key) ? o.get(key).getAsString() : def;
    }

    private double getDouble(JsonObject o, String key, double def) {
        return o.has(key) ? o.get(key).getAsDouble() : def;
    }

    private int getInt(JsonObject o, String key, int def) {
        return o.has(key) ? o.get(key).getAsInt() : def;
    }

    private boolean getBoolean(JsonObject o, String key, boolean def) {
        return o.has(key) && o.get(key).getAsBoolean();
    }

    private void ensureTemplate(Path file, List<String> fallbackModes) {
        if (Files.exists(file)) return;
        try {
            Files.createDirectories(file.getParent());
            JsonObject rootJson = new JsonObject();
            rootJson.addProperty("description", "Добавляй режимы в modes и любые settings (slider/boolean/mode/color)");

            JsonArray modes = new JsonArray();
            for (String mode : fallbackModes) {
                modes.add(mode);
            }
            rootJson.add("modes", modes);

            JsonArray settings = new JsonArray();
            JsonObject slider = new JsonObject();
            slider.addProperty("type", "slider");
            slider.addProperty("name", "custom_speed");
            slider.addProperty("default", 1.0);
            slider.addProperty("min", 0.0);
            slider.addProperty("max", 100.0);
            slider.addProperty("step", 0.5);
            settings.add(slider);

            JsonObject bool = new JsonObject();
            bool.addProperty("type", "boolean");
            bool.addProperty("name", "strict_check");
            bool.addProperty("default", false);
            settings.add(bool);

            rootJson.add("settings", settings);

            JsonObject values = new JsonObject();
            values.addProperty("mode", fallbackModes.isEmpty() ? "Default" : fallbackModes.getFirst());
            values.addProperty("custom_speed", 1.0);
            rootJson.add("values", values);

            JsonArray templates = new JsonArray();
            JsonObject smooth = new JsonObject();
            smooth.addProperty("name", "Плавная");
            smooth.addProperty("pitch", "*=0.35");
            smooth.addProperty("yaw", "*=0.40");
            templates.add(smooth);
            JsonObject snap = new JsonObject();
            snap.addProperty("name", "Резкая");
            snap.addProperty("pitch", "*=1.00");
            snap.addProperty("yaw", "*=1.00");
            templates.add(snap);
            rootJson.add("rotationTemplates", templates);

            Files.writeString(file, GSON.toJson(rootJson));
        } catch (IOException ignored) {
        }
    }
}
