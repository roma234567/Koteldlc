package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class HandChams extends PremiumModule {
    public HandChams() {
        super("HandChams", "Hand shader color and transparency settings.", ModuleCategory.VISUAL);
        number("Alpha", 0.55, 0.0, 1.0);
        mode("Shader", "Glass", "Glass", "Flat", "Pulse");
        status("Configured");
    }
}
