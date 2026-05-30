package Kotel.core.setting;

import java.util.List;

public class ModeSetting extends Setting<String> {
    private final List<String> modes;
    public ModeSetting(String name, String value, List<String> modes) { super(name, value); this.modes = List.copyOf(modes); }
    @Override public void setValue(String value) { if (!modes.contains(value)) throw new IllegalArgumentException("Unknown mode: " + value); super.setValue(value); }
    public List<String> modes() { return modes; }
}
