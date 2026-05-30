package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class TargetHUD extends PremiumModule {
    public TargetHUD() {
        super("TargetHUD", "Premium target information card.", ModuleCategory.VISUAL);
        mode("Theme", "Lucky", "Lucky", "Minimal", "Glass");
        bool("Health Bar", true);
        status("Configured");
    }
}
