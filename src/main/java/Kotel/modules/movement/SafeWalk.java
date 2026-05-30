package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class SafeWalk extends PremiumModule {
    public SafeWalk() {
        super("SafeWalk", "Edge safety overlay for bridging and building practice.", ModuleCategory.MOVEMENT);
        number("Edge Warning", 0.35, 0.05, 1.0);
        bool("Highlight Blocks", true);
        status("Configured");
    }
}
