package com.koteldlc.client.module;

import com.koteldlc.client.gui.settings.Setting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Module {
    private final String name;
    private final String description;
    private int key;
    private boolean toggled;
    private final Category category;
    private final List<Setting<?>> settings = new ArrayList<>();

    protected Module(String name, String description, int key, Category category) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.category = category;
    }

    protected <T extends Setting<?>> T register(T setting) {
        settings.add(setting);
        return setting;
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) onEnable(); else onDisable();
    }

    public void setToggled(boolean state) {
        if (toggled != state) toggle();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }
    public boolean isToggled() { return toggled; }
    public Category getCategory() { return category; }
    public List<Setting<?>> getSettings() { return Collections.unmodifiableList(settings); }
}
