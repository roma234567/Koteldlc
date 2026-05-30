package Kotel.core.resources;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResourceLoader {
    private final String namespace;
    private final Map<String, String> resources = new LinkedHashMap<>();
    public ResourceLoader(String namespace) { this.namespace = namespace; }
    public void prepareDefaults() { resources.put("theme", namespace + ":config/default-theme.json"); resources.put("font", namespace + ":font/inter.ttf"); }
    public Map<String, String> resources() { return Map.copyOf(resources); }
}
