package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class ChinaHat extends PremiumModule {
    public ChinaHat() {
        super("ChinaHat", "Cosmetic cone/halo above the player model.", ModuleCategory.VISUAL);
        number("Radius", 0.75, 0.2, 2.0);
        mode("Animation", "Spin", "Spin", "Pulse", "Static");
        status("Configured");
    }
}
