package Kotel.modules.player;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class NoSlowBreak extends PremiumModule {
    public NoSlowBreak() {
        super("NoSlowBreak", "Mining slowdown analyzer and break-progress HUD.", ModuleCategory.PLAYER);
        bool("Show Efficiency", true);
        number("Alert Below", 0.3, 0.0, 1.0);
        status("Configured");
    }
}
