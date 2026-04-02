package com.koteldlc.client.gui.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ModeSetting extends Setting<String> {
    private final List<String> modes;

    public ModeSetting(String name, String defaultMode, List<String> modes) {
        super(name, defaultMode);
        this.modes = new ArrayList<>(modes);
        if (!this.modes.contains(defaultMode) && !this.modes.isEmpty()) {
            setValue(this.modes.getFirst());
        }
    }

    public List<String> getModes() {
        return Collections.unmodifiableList(modes);
    }

    public void addMode(String mode) {
        if (mode == null || mode.isBlank() || modes.contains(mode)) {
            return;
        }
        modes.add(mode);
    }

    public void addModes(Collection<String> values) {
        if (values == null) {
            return;
        }
        for (String value : values) {
            addMode(value);
        }
    }
}
