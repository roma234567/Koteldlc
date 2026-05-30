package Kotel.gui.clickgui;

import Kotel.core.module.Module;
import Kotel.core.module.ModuleCategory;
import Kotel.core.module.ModuleManager;
import java.util.EnumMap;
import java.util.Map;

public class ClickGUI {
    private final Map<ModuleCategory, Panel> panels = new EnumMap<>(ModuleCategory.class);
    public void rebuild(ModuleManager manager) {
        panels.clear();
        for (ModuleCategory category : ModuleCategory.values()) panels.put(category, new Panel(category.name()));
        for (Module module : manager.modules()) panels.get(module.category()).add(out -> out.append("  - ").append(module.name()).append("\n"));
    }
    public String preview() { StringBuilder out = new StringBuilder("KotelDLC ClickGUI\n"); panels.values().forEach(panel -> panel.render(out)); return out.toString(); }
}
