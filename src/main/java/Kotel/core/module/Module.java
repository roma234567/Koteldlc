package Kotel.core.module;

import Kotel.core.setting.Setting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Module {
    private final String name;
    private final String description;
    private final ModuleCategory category;
    private final List<Setting<?>> settings = new ArrayList<>();
    private boolean enabled;

    protected Module(String name, String description, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public final void toggle() { setEnabled(!enabled); }
    public final void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) onEnable(); else onDisable();
    }

    protected void onEnable() { }
    protected void onDisable() { }
    protected <T extends Setting<?>> T add(T setting) { settings.add(setting); return setting; }

    public String name() { return name; }
    public String description() { return description; }
    public ModuleCategory category() { return category; }
    public boolean enabled() { return enabled; }
    public List<Setting<?>> settings() { return Collections.unmodifiableList(settings); }
}
