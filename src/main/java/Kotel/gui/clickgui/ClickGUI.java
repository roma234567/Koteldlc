package Kotel.gui.clickgui;

import Kotel.core.module.Module;
import Kotel.core.module.ModuleCategory;
import Kotel.core.module.ModuleManager;
import Kotel.core.theme.ThemeManager;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ClickGUI {
    private final ThemeManager themeManager;
    private final Map<ModuleCategory, Panel> panels = new EnumMap<>(ModuleCategory.class);
    private GuiView activeView = GuiView.LUCKY_CARDS;
    private String search = "";

    public ClickGUI(ThemeManager themeManager) {
        this.themeManager = themeManager;
    }

    public void rebuild() {
        panels.clear();
        int index = 0;
        for (ModuleCategory category : ModuleCategory.values()) {
            panels.put(category, new Panel(category.name(), 190 + (index % 2) * 450, 280 + (index / 2) * 170, 420));
            index++;
        }
        for (Module module : ModuleManager.modules()) {
            if (!search.isBlank() && !module.name().toLowerCase().contains(search.toLowerCase())) continue;
            panels.get(module.category()).add(new ModuleCard(module, 0, 0, 400, activeView == GuiView.LUCKY_CARDS ? 72 : 28));
        }
    }

    public void switchView(GuiView view) {
        this.activeView = view;
        rebuild();
    }

    public void search(String search) {
        this.search = search == null ? "" : search.trim();
        rebuild();
    }

    public String preview() {
        StringBuilder out = new StringBuilder("KotelDLC ClickGUI • ")
                .append(themeManager.name())
                .append(" • view=").append(activeView)
                .append("\nTabs: Combat | Player | Movement | Render | Other\nSearch: ").append(search.isBlank() ? "<empty>" : search)
                .append("\n");
        orderedPanels().forEach(panel -> panel.render(out));
        return out.toString();
    }

    private List<Panel> orderedPanels() {
        return List.of(
                panels.get(ModuleCategory.COMBAT),
                panels.get(ModuleCategory.PLAYER),
                panels.get(ModuleCategory.MOVEMENT),
                panels.get(ModuleCategory.VISUAL),
                panels.get(ModuleCategory.MISC)
        );
    }
}
