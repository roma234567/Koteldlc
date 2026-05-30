package Kotel.core.theme;

public class ThemeManager {
    private String name = "Lucky Dark";
    private int accent = 0xFFFFFFFF;
    private int accentSoft = 0xFFE6D4A8;
    private int background = 0xF2070708;
    private int panel = 0xE6111113;
    private int panelHover = 0xF21A1A1D;
    private int text = 0xFFF8F8F8;
    private int mutedText = 0xFF8D8D94;

    public String name() { return name; }
    public int accent() { return accent; }
    public int accentSoft() { return accentSoft; }
    public int background() { return background; }
    public int panel() { return panel; }
    public int panelHover() { return panelHover; }
    public int text() { return text; }
    public int mutedText() { return mutedText; }
    public void setAccent(int accent) { this.accent = accent; }
}
