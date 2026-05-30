package Kotel.gui.menu;

import Kotel.core.theme.ThemeManager;
import java.util.ArrayList;
import java.util.List;

public class CustomMainMenu {
    private final ThemeManager themeManager;
    private final List<CustomButton> buttons = new ArrayList<>();

    public CustomMainMenu(ThemeManager themeManager) { this.themeManager = themeManager; }

    public void rebuild() {
        buttons.clear();
        buttons.add(new CustomButton("Singleplayer", 48, 116, 210, 34));
        buttons.add(new CustomButton("Multiplayer", 48, 160, 210, 34));
        buttons.add(new CustomButton("ClickGUI", 48, 204, 210, 34));
        buttons.add(new CustomButton("Settings", 48, 248, 210, 34));
        buttons.add(new CustomButton("Quit", 48, 292, 210, 34));
    }

    public String title() { return "✱ KotelDLC"; }
    public List<CustomButton> buttons() { return List.copyOf(buttons); }
    public int accentColor() { return themeManager.accent(); }
}
