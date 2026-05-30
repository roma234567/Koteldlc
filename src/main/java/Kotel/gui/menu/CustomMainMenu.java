package Kotel.gui.menu;

import Kotel.core.theme.ThemeManager;
import java.util.ArrayList;
import java.util.List;

public class CustomMainMenu {
    private final ThemeManager themeManager;
    private final List<CustomButton> buttons = new ArrayList<>();
    public CustomMainMenu(ThemeManager themeManager) { this.themeManager = themeManager; }
    public void rebuild() { buttons.clear(); buttons.add(new CustomButton("Singleplayer", 24, 72, 172, 28)); buttons.add(new CustomButton("Multiplayer", 24, 108, 172, 28)); buttons.add(new CustomButton("Settings", 24, 144, 172, 28)); }
    public List<CustomButton> buttons() { return List.copyOf(buttons); }
    public int accentColor() { return themeManager.accent(); }
}
