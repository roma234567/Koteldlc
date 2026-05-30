package Kotel.client;

import Kotel.core.module.ModuleManager;
import Kotel.core.resources.ResourceLoader;
import Kotel.core.theme.ThemeManager;
import Kotel.gui.menu.CustomMainMenu;

/** Entry point for the KotelDLC client-side utility scaffold. */
public final class KotelMod {
    public static final String NAME = "KotelDLC";
    public static final String VERSION = "1.0.0";

    private static final ThemeManager THEME_MANAGER = new ThemeManager();
    private static final ResourceLoader RESOURCE_LOADER = new ResourceLoader("koteldlc");
    private static final ModuleManager MODULE_MANAGER = new ModuleManager();
    private static final CustomMainMenu MAIN_MENU = new CustomMainMenu(THEME_MANAGER);

    private KotelMod() {
    }

    public static void initialize() {
        RESOURCE_LOADER.prepareDefaults();
        MODULE_MANAGER.registerDefaults();
        MAIN_MENU.rebuild();
    }

    public static ThemeManager themes() { return THEME_MANAGER; }
    public static ResourceLoader resources() { return RESOURCE_LOADER; }
    public static ModuleManager modules() { return MODULE_MANAGER; }
    public static CustomMainMenu mainMenu() { return MAIN_MENU; }
}
