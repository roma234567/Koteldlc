package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class BetterMinecraft extends PremiumModule {
    public BetterMinecraft() {
        super("BetterMinecraft", "Quality-of-life interface polish collection.", ModuleCategory.VISUAL);
        bool("Better Tooltips", true);
        bool("Clean Chat", true);
        status("Configured");
    }
}
