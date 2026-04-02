package com.koteldlc.client.config.modules;

import com.google.gson.*;

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

        List<String> result = new ArrayList<>(fallbackModes);
        if (!Files.exists(file)) {
            return result;
        }

        try {
            JsonObject json = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
            if (json.has("modes") && json.get("modes").isJsonArray()) {
                for (JsonElement element : json.getAsJsonArray("modes")) {
                    if (element.isJsonPrimitive()) {
                        String mode = element.getAsString();
                        if (!mode.isBlank() && !result.contains(mode)) {
                            result.add(mode);
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }

        return result;
    }

    private void ensureTemplate(Path file, List<String> fallbackModes) {
        if (Files.exists(file)) {
            return;
        }
        try {
            Files.createDirectories(file.getParent());
            JsonObject rootJson = new JsonObject();
            rootJson.addProperty("description", "Добавляй свои режимы в массив modes — они появятся в настройках модуля");
            JsonArray modes = new JsonArray();
            for (String mode : fallbackModes) {
                modes.add(mode);
            }
            rootJson.add("modes", modes);
            JsonArray templates = new JsonArray();
            JsonObject smooth = new JsonObject();
            smooth.addProperty("name", "Smooth");
            smooth.addProperty("pitch", "*=0.35");
            smooth.addProperty("yaw", "*=0.40");
            templates.add(smooth);
            JsonObject snap = new JsonObject();
            snap.addProperty("name", "Snap");
            snap.addProperty("pitch", "*=1.00");
            snap.addProperty("yaw", "*=1.00");
            templates.add(snap);
            rootJson.add("rotationTemplates", templates);
            Files.writeString(file, GSON.toJson(rootJson));
        } catch (IOException ignored) {
        }
    }
}
