package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class HUD extends PremiumModule {
    public HUD() {
        super("HUD", "Main KotelDLC heads-up display.", ModuleCategory.VISUAL);
        mode("Layout", "Premium", "Premium", "Compact", "Streamer");
        bool("Watermark", true);
        status("Configured");
    }
}
