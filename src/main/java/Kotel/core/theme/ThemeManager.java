package Kotel.core.theme;

public class ThemeManager {
    private int accent = 0xFFFF7A1A;
    private int background = 0xCC111217;
    private int panel = 0xEE1B1D25;

    public int accent() { return accent; }
    public int background() { return background; }
    public int panel() { return panel; }
    public void setAccent(int accent) { this.accent = accent; }
}
