package com.koteldlc.client.gui.settings;

import java.util.List;

public class ModeSetting extends Setting<String> {
    private final List<String> modes;
    public ModeSetting(String name, String defaultMode, List<String> modes) {
        super(name, defaultMode);
        this.modes = modes;
    }
    public List<String> getModes() { return modes; }
}
