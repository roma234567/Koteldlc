package Kotel.client;

import Kotel.core.module.ModuleManager;
import Kotel.core.resources.ResourceLoader;
import Kotel.core.theme.ThemeManager;
import Kotel.gui.clickgui.ClickGUI;
import Kotel.gui.menu.CustomMainMenu;
import Kotel.modules.combat.*;
import Kotel.modules.misc.*;
import Kotel.modules.movement.*;
import Kotel.modules.player.*;
import Kotel.modules.visual.*;

/** Entry point for the KotelDLC premium client scaffold. */
public final class KotelMod {
    public static final String NAME = "KotelDLC";
    public static final String VERSION = "1.0.0";

    private static final ThemeManager THEME_MANAGER = new ThemeManager();
    private static final ResourceLoader RESOURCE_LOADER = new ResourceLoader("koteldlc");
    private static final CustomMainMenu MAIN_MENU = new CustomMainMenu(THEME_MANAGER);
    private static final ClickGUI CLICK_GUI = new ClickGUI(THEME_MANAGER);

    private KotelMod() {
    }

    public static void onInitialize() {
        RESOURCE_LOADER.prepareDefaults();
        registerModules();
        MAIN_MENU.rebuild();
        CLICK_GUI.rebuild();
    }

    public static void initialize() {
        onInitialize();
    }

    public void onInitializeClient() {
        onInitialize();
    }

    public static void registerModules() {
        if (!ModuleManager.modules().isEmpty()) return;

        // Combat
        ModuleManager.register(new KillAura());
        ModuleManager.register(new Hitboxes());
        ModuleManager.register(new AutoTotemModule());
        ModuleManager.register(new TriggerBot());
        ModuleManager.register(new AimAssistModule());

        // Movement
        ModuleManager.register(new Speed());
        ModuleManager.register(new Fly());
        ModuleManager.register(new NoFall());
        ModuleManager.register(new NoSlow());
        ModuleManager.register(new Strafe());
        ModuleManager.register(new Jesus());
        ModuleManager.register(new LongJump());
        ModuleManager.register(new Sprint());
        ModuleManager.register(new SafeWalk());
        ModuleManager.register(new InventoryMove());
        ModuleManager.register(new ElytraGlide());

        // Visual
        ModuleManager.register(new ESP());
        ModuleManager.register(new TargetHUD());
        ModuleManager.register(new HUD());
        ModuleManager.register(new Chams());
        ModuleManager.register(new TargetESP());
        ModuleManager.register(new ProjectilePrediction());
        ModuleManager.register(new Arrows());
        ModuleManager.register(new Fullbright());
        ModuleManager.register(new HandChams());
        ModuleManager.register(new JumpCircle());
        ModuleManager.register(new KillEffect());
        ModuleManager.register(new HitEffect());
        ModuleManager.register(new BlockESP());
        ModuleManager.register(new ChinaHat());
        ModuleManager.register(new CrossHair());
        ModuleManager.register(new SwingAnimation());
        ModuleManager.register(new ViewModel());
        ModuleManager.register(new CameraSettings());
        ModuleManager.register(new AspectRatio());
        ModuleManager.register(new FreeCam());
        ModuleManager.register(new NoRender());
        ModuleManager.register(new BetterMinecraft());
        ModuleManager.register(new SeeInvisible());
        ModuleManager.register(new AuctionHelper());
        ModuleManager.register(new Particles());

        // Misc
        ModuleManager.register(new AutoAccept());
        ModuleManager.register(new AutoAuth());
        ModuleManager.register(new AutoRespawn());
        ModuleManager.register(new AutoResell());
        ModuleManager.register(new AutoJoin());
        ModuleManager.register(new AutoDuels());
        ModuleManager.register(new Disabler());
        ModuleManager.register(new NameProtect());
        ModuleManager.register(new AntiCheatDetector());
        ModuleManager.register(new InventoryCleaner());

        // Player
        ModuleManager.register(new AutoArmor());
        ModuleManager.register(new AutoTool());
        ModuleManager.register(new NoSlowBreak());
    }

    public static ThemeManager themes() { return THEME_MANAGER; }
    public static ResourceLoader resources() { return RESOURCE_LOADER; }
    public static ModuleManager modules() { return new ModuleManager(); }
    public static CustomMainMenu mainMenu() { return MAIN_MENU; }
    public static ClickGUI clickGui() { return CLICK_GUI; }
}
